package extensions.pouchdb

import util.undo.Command
import util.undo.UndoComponent
import kotlinext.js.jsObject
import kotlinx.coroutines.await
import pouchdb.Database
import pouchdb.Document
import pouchdb.core.*
import util.*
import util.Revision
import kotlin.js.Promise

fun <JS : EntityJS<T>, T : Entity<T>> Database<JS>.get(id: ID<T>): Promise<JS> {
    return get(id._id)
}

suspend fun <JS : EntityJS<T>, T : Entity<T>> Database<JS>.createAndGetId(entity: Entity<T>, toNormal: JS.() -> T): ID<T> {
    return create(entity, toNormal).id.toID()!!
}

suspend fun <JS : EntityJS<T>, T : Entity<T>> Database<JS>.create(entity: Entity<T>, toNormal: JS.() -> T): Response {
    return save(null, entity, toNormal)
}

suspend fun <JS : EntityJS<T>, T : Entity<T>> Database<JS>.save(
    priorEntity: Entity<T>?,
    entity: Entity<T>,
    toNormal: JS.() -> T
): Response {
    val entityWithId = entity.withGeneratedIdIfMissing(priorEntity)
    return try  {
        put(entityWithId).await()
    } catch (err: Throwable) {
        if (err.unsafeCast<Error>().name == "conflict" && priorEntity != null) {
            val persistedValue = get(entityWithId.requiredId).await().toNormal()
            if (persistedValue == priorEntity.withRevision(persistedValue.requiredRev)) {
                val response = put(entityWithId.withRevision(persistedValue.requiredRev)).await()
                console.info("Resolved PouchDB conflict")
                response
            } else {
                throw err
            }
        } else {
            throw err
        }
    }
}

private fun <T : Entity<T>> Entity<T>.withGeneratedIdIfMissing(priorEntity: Entity<T>?) =
    if (id != null) this else withID(priorEntity?.id ?: ID(IdGenerator.generateID()))

fun <JS : EntityJS<T>, T : Entity<T>> Database<JS>.remove(entity: Entity<T>): Promise<Response> {
    return remove(entity.requiredId._id, entity.requiredRev._rev)
}

suspend fun <JS : EntityJS<T>, T : Entity<T>> Database<JS>.saveAllowingUndo(priorEntity: Entity<T>?, entity: Entity<T>, toNormal: JS.() -> T): ID<T> {
    val entityWithId = entity.withGeneratedIdIfMissing(priorEntity)
    val command = if (priorEntity == null) {
        object : Command("Added $entityWithId") {
            private val self = this

            override suspend fun executeAndGetOpposite(): Command {
                val response = create(entityWithId, toNormal)
                val addedEntity = entityWithId.withRevision(Revision(response.rev))
                return object : Command("Deleted $addedEntity") {
                    override suspend fun executeAndGetOpposite(): Command {
                        remove(addedEntity).await()
                        return self
                    }
                }
            }
        }
    } else {
        object : Command("Saved $entityWithId") {
            private val self = this

            override suspend fun executeAndGetOpposite(): Command {
                val response = save(priorEntity, entityWithId, toNormal)
                return object : Command("Reverted $priorEntity") {
                    override suspend fun executeAndGetOpposite(): Command {
                        save(entityWithId, priorEntity.withRevision(Revision(response.rev)), toNormal)
                        return self
                    }
                }
            }
        }
    }
    UndoComponent.addUndoCommand(command.executeAndGetOpposite())
    return entityWithId.requiredId
}

suspend fun <JS : EntityJS<T>, T : Entity<T>> Database<JS>.removeAllowingUndo(entity: Entity<T>, toNormal: JS.() -> T) {
    val command = object : Command("Removed $entity") {
        private val self = this

        override suspend fun executeAndGetOpposite(): Command {
            val response = remove(entity).await()
            val addedEntity = entity.withRevision(Revision(response.rev))
            return object : Command("Added $addedEntity") {
                override suspend fun executeAndGetOpposite(): Command {
                    create(entity.withRevision(Revision(response.rev)), toNormal)
                    return self
                }
            }
        }
    }
    UndoComponent.addUndoCommand(command.executeAndGetOpposite())
}

fun <JS : EntityJS<*>> Changes<JS>.onChange(listener: (change: ChangesResponseChange<JS>) -> Any): Changes<JS> {
    return on("change", listener)
}

fun <Content : EntityJS<*>> Changes<Content>.onComplete(listener: (info: ChangesResponse<Content>) -> Any): Changes<Content> {
    return on("complete", listener)
}

fun <Content : EntityJS<*>> Changes<Content>.onError(listener: (err: Any) -> Any): Changes<Content> {
    return on("error", listener)
}

fun <Content : EntityJS<T>, T : Entity<T>> Database<Content>.onChangedMap(toNormal: Document<Content>.() -> T, listener: (Map<ID<T>,T>) -> Unit): Changes<Content> {
    val entityById = mutableMapOf<ID<T>, T>()
    allDocs(options = jsObject<AllDocsOptions> {
        include_docs = true
    }).then { allDocsResponse ->
        entityById.putAll(allDocsResponse.rows.map { it.doc.toNormal() }.map { it.requiredId to it }.toMap())
        listener(entityById)
    }
    return changes(options = jsObject {
        live = true
        since = "now"
        include_docs = true
    }).onChange { change ->
        if (change.deleted == true) {
            entityById.remove(ID(change.id))
        } else { // updated/inserted
            val entity = change.doc.toNormal()
            entityById[entity.requiredId] = entity
        }
        listener(entityById)
    }
}

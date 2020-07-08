package component.entity

data class EntityForTesting(val name: String, val _id: ID<EntityForTesting>? = null, val _rev: Revision<EntityForTesting>? = null) : Entity<EntityForTesting> {

    override val id: ID<EntityForTesting>? get() = _id

    override val rev: Revision<EntityForTesting>? = _rev

    override fun withID(id: ID<EntityForTesting>): EntityForTesting = copy(_id = id)

    override fun withRevision(revision: Revision<EntityForTesting>): EntityForTesting = copy(_rev = revision)
}

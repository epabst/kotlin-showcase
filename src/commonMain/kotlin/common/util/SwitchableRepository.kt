package common.util

/**
 * A [Repository] whose underlying Repository can be switched via a [net.yested.core.properties.Property].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 2/7/18
 * Time: 4:56 AM
 */
open class SwitchableRepository<T : WithID<T>>(
        delegate: Repository<T>,
        private val undoProvider: UndoProvider = UndoProvider.empty) : Repository<T> {
    private var _delegate: Repository<T> = delegate
    private val listeners: ArrayList<RepositoryListener<T>> = ArrayList(4)

    var delegate: Repository<T>
        get() = _delegate
        set(delegate) {
            if (delegate != _delegate) {
                undoProvider.notUndoable {
                    listeners.forEach { listener ->
                        _delegate.removeListener(listener)
                        delegate.addListener(listener)
                        _delegate.list().forEach { listener.onVisibilityChanged(it, false) }
                        delegate.list().forEach { listener.onVisibilityChanged(it, true) }
                    }
                    _delegate = delegate
                }
            }
        }

    override fun generateID(): ID<T> {
        return delegate.generateID()
    }

    override fun find(id: ID<T>): T? = delegate.find(id)

    override fun list(): List<T> = delegate.list()

    override fun save(original: T?, replacement: T): ID<T> {
        return delegate.save(original, replacement)
    }

    override fun remove(id: ID<T>): Boolean {
        return delegate.remove(id)
    }

    override fun addListener(listener: RepositoryListener<T>) {
        listeners += listener
        delegate.addListener(listener)
    }

    override fun removeListener(listener: RepositoryListener<T>) {
        delegate.removeListener(listener)
        listeners -= listener
    }

    override val localStorageKeys: Set<String>
        get() = delegate.localStorageKeys
}

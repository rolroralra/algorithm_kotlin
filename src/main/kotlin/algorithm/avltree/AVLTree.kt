package algorithm.avltree

class AVLNode<T>(
    var value: T,
    var left: AVLNode<T>? = null,
    var right: AVLNode<T>? = null,
    var height: Int = 1
)

/**
 * Self-balancing binary search tree. Duplicate inserts are ignored.
 */
class AVLTree<T>(
    values: List<T> = emptyList(),
    private val comparator: Comparator<T>
) {
    var root: AVLNode<T>? = null
        private set

    init {
        values.forEach { insert(it) }
    }

    fun insert(value: T) {
        TODO("Implement AVL insert with rebalancing")
    }

    fun delete(value: T) {
        TODO("Implement AVL delete with rebalancing")
    }

    fun contains(value: T): Boolean {
        TODO("Implement contains lookup")
    }

    fun size(): Int {
        TODO("Implement size tracking")
    }

    fun isEmpty(): Boolean {
        TODO("Implement isEmpty check")
    }

    fun height(): Int {
        TODO("Implement tree height lookup")
    }

    fun inorder(): List<T> {
        TODO("Implement inorder traversal")
    }

    fun findMin(): T? {
        TODO("Implement minimum value lookup")
    }

    fun findMax(): T? {
        TODO("Implement maximum value lookup")
    }
}

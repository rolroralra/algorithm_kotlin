package algorithm.redblacktree

enum class Color {
    RED,
    BLACK
}

class RBNode<T>(
    var value: T,
    var color: Color = Color.RED,
    var left: RBNode<T>? = null,
    var right: RBNode<T>? = null
)

/**
 * Left-leaning red-black tree. Duplicate inserts are ignored.
 */
class RedBlackTree<T>(
    values: List<T> = emptyList(),
    private val comparator: Comparator<T>
) {
    var root: RBNode<T>? = null
        private set

    init {
        values.forEach { insert(it) }
    }

    fun insert(value: T) {
        TODO("Implement LLRB insert with color flips and rotations")
    }

    fun delete(value: T) {
        TODO("Implement LLRB delete with color flips and rotations")
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

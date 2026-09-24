package algorithm.segmenttree.fenwicktree

/**
 * 1-indexed Fenwick tree over [size] elements. [update] adds a diff to the existing value at [index].
 */
class FenwickTree(private val size: Int) {
    fun update(index: Int, diff: Int) {
        TODO("Implement diff-based point update")
    }

    fun query(leftIndexInclusive: Int, rightIndexInclusive: Int): Int {
        TODO("Implement range-sum query")
    }
}

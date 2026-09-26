package algorithm.segmenttree.fenwicktree

import kotlin.collections.toList
import kotlin.collections.withIndex

/**
 * 1-indexed Fenwick tree over [size] elements. [update] adds a diff to the existing value at [index].
 */
class FenwickTree(private val size: Int) {
    // The Fenwick tree is implemented as a 1-indexed array,
    // so we create a list of size + 1 to accommodate the 1-based indexing.
    private val fenwickTree: MutableList<Int> = MutableList(size + 1) { 0 }

    constructor(originDataArray: Array<Int>): this(originDataArray.toList())

    constructor(originDataList: List<Int>): this(originDataList.size) {
        for ((index, value) in originDataList.withIndex()) {
            update(index, value)
        }
    }

    /**
     * Update the value at [index] by adding [diff] to it. The [index] is 0-based.
     *
     * @param index base-0 index
     * @param diff difference for update
     *
     */
    fun update(index: Int, diff: Int) {
        var currIndex = index + 1   // convert to 1-based index

        while (currIndex <= size) {
            fenwickTree[currIndex] += diff
            currIndex += currIndex.takeLowestOneBit()
        }
    }

    /**
     * Returns the sum of the elements in the range ([leftIndexInclusive], [rightIndexInclusive]). Both indices are 0-based.
     *
     * @param leftIndexInclusive base-0 index
     * @param rightIndexInclusive base-0 index
     *
     * @return sum which index in range(leftIndexInclusive, rightIndexInclusive)
     */
    fun query(leftIndexInclusive: Int, rightIndexInclusive: Int): Int {
        return query(rightIndexInclusive + 1) - query(leftIndexInclusive)
    }

    /**
     * Internal method to compute the prefix sum from index 1 to [index]. The [index] is 1-based.
     *
     * @param index base-1 index
     *
     * @return sum which index in range(1, index)
     */
    private fun query(index: Int): Int {
        var result = 0

        var currIndex = index
        while (currIndex > 0) {
            result += fenwickTree[currIndex]
            currIndex -= currIndex.takeLowestOneBit()
        }

        return result
    }
}

package algorithm.segmenttree.fenwicktree

import kotlin.collections.toList
import kotlin.collections.withIndex

/**
 * 1-indexed Fenwick tree over [size] elements. [update] adds a diff to the existing value at [index].
 */
class FenwickTree(private val size: Int) {
    // base index = 1
    private val fenwickTree: MutableList<Int> = MutableList(size + 1) { 0 }

    constructor(originDataArray: Array<Int>): this(originDataArray.toList())

    constructor(originDataList: List<Int>): this(originDataList.size) {
        for ((index, value) in originDataList.withIndex()) {
            update(index, value)
        }
    }

    /**
     * @param index base-1 index
     * @param diff difference for update
     *
     */
    fun update(index: Int, diff: Int) {
        var currIndex = index

        while (currIndex <= size) {
            fenwickTree[currIndex] += diff
            currIndex += currIndex.takeLowestOneBit()
        }
    }

    /**
     * @param leftIndexInclusive
     * @param rightIndexInclusive
     *
     * @return sum which index in range(leftIndexInclusive, rightIndexInclusive)
     */
    fun query(leftIndexInclusive: Int, rightIndexInclusive: Int): Int {
        return query(rightIndexInclusive) - query(leftIndexInclusive - 1)
    }

    /**
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

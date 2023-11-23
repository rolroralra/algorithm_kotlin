package algorithm

import java.util.*
import java.util.function.BinaryOperator

class SegmentTree<T> (
    size: Int,
    accumulator: BinaryOperator<T>
){
    private val originDataSize = size
    private val segmentTree: MutableList<T?>
    private val accumulator: BinaryOperator<T?> = BinaryOperator { a, b ->
        if (a != null && b != null) {
            accumulator.apply(a, b)
        } else a ?: b
    }

    constructor(originDataArray: Array<T>, accumulator: BinaryOperator<T>) : this(originDataArray.size, accumulator) {
        init(originDataArray)
    }

    constructor(originDataList: List<T>, accumulator: BinaryOperator<T>) : this(originDataList.size, accumulator) {
        init(originDataList)
    }

    init {
        require(size > 0) { "size must be positive" }

        var segmentTreeSize = 1
        while (segmentTreeSize < size) {
            segmentTreeSize = segmentTreeSize shl 1
        }

        segmentTreeSize = segmentTreeSize.shl(1) - 1

        this.segmentTree = MutableList(segmentTreeSize) { null }
    }

    /**
     * @param index index of origin data
     * @param value value of origin data
     * update value of segment tree
     */
    fun update(index: Int, value: T) {
        this.updateByValue(index, value, ROOT_NODE_BASE_INDEX, 0, this.originDataSize - 1)
    }

    /**
     * @param left  left Index of Query Range
     * @param right right index of Query Range
     * @return the result of query
     */
    fun query(left: Int, right: Int): T? {
        return query(left, right, ROOT_NODE_BASE_INDEX, 0, this.originDataSize - 1)
    }

    @Throws(Exception::class)
    private fun init(originDataArray: Array<T>) {
        init(originDataArray.toList())
    }

    @Throws(Exception::class)
    private fun init(originDataList: List<T>) {
        if (originDataList.size > this.originDataSize) {
            throw Exception(
                ("Input Data size is overflow! (Input Data Size : " + originDataList.size +
                        ", Current Data Size : " + this.originDataSize).toString() + ")"
            )
        }

        for (indexedValue in originDataList.withIndex()) {
            update(indexedValue.index, indexedValue.value)
        }
    }

    /**
     * @param index index of origin data
     * @param value value of origin data
     * @param node  current node index
     * @param nodeLeft  left index of current node
     * @param nodeRight right index of current node
     *
     * update value of segment tree
     */
    private fun updateByValue(index: Int, value: T, node: Int, nodeLeft: Int, nodeRight: Int) {
        // Escaping Condition : index is not in segment [nodeLeft, nodeRight]
        if (index < nodeLeft || nodeRight < index) {
            return
        }
        if (nodeLeft == nodeRight) {
            segmentTree[node] = value
            return
        }
        val nodeMid = nodeLeft + nodeRight shr 1
        val leftNode = (node shl 1) + 1
        val rightNode = leftNode + 1
        updateByValue(index, value, leftNode, nodeLeft, nodeMid)
        updateByValue(index, value, rightNode, nodeMid + 1, nodeRight)

        when {
            segmentTree[leftNode] != null && segmentTree[rightNode] != null -> {
                segmentTree[node] = accumulator.apply(segmentTree[leftNode], segmentTree[rightNode])
            }
            segmentTree[leftNode] != null -> {
                segmentTree[node] = segmentTree[leftNode]
            }
            segmentTree[rightNode] != null -> {
                segmentTree[node] = segmentTree[rightNode]
            }
        }
    }

    /**
     * @param left  left Index of Query Range
     * @param right right index of Query Range
     * @param node  current node index
     * @param nodeLeft  left index of current node
     * @param nodeRight right index of current node
     * @return the result of query
     */
    private fun query(left: Int, right: Int, node: Int, nodeLeft: Int, nodeRight: Int): T? {
        // [left, right] is not in [nodeLeft, nodeRight]
        if (left > nodeRight || right < nodeLeft) {
            return null
        }

        // [left ... [nodeLeft ... nodeRight] ... right]
        if (left <= nodeLeft && nodeRight <= right) {
            return this.segmentTree[node]
        }

        // Divide & Conquer
        val nodeMid = nodeLeft + nodeRight shr 1
        val leftNode = (node shl 1) + 1
        val rightNode = leftNode + 1
        val leftResult = query(left, right, leftNode, nodeLeft, nodeMid)
        val rightResult = query(left, right, rightNode, nodeMid + 1, nodeRight)

        return if (leftResult != null && rightResult != null) {
            accumulator.apply(leftResult, rightResult)
        } else leftResult ?: rightResult
    }

    companion object {
        private const val ROOT_NODE_BASE_INDEX = 0
    }
}

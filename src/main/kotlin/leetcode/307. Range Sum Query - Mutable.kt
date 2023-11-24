package leetcode

fun main() {
val numArray = NumArray(intArrayOf(1, 3, 5))
    println(numArray.sumRange(0, 2)) // 9
    numArray.update(1, 2)
    println(numArray.sumRange(0, 2)) // 8
}

private class NumArray(nums: IntArray) {
    private val originDataSize: Int = nums.size
    private val tree: IntArray

    init {
        val size = generateSequence(1) { it shl 1 }
            .dropWhile { it < nums.size }
            .first() * 2 - 1

        tree = IntArray(size) { 0 }

        nums.forEachIndexed { index, num ->
            update(index, num)
        }
    }

    fun update(index: Int, `val`: Int) {
        update(index, `val`, ROOT_NODE_BASE_INDEX, ROOT_NODE_BASE_INDEX, originDataSize - 1)
    }

    fun sumRange(left: Int, right: Int): Int {
        return sumRange(left, right, ROOT_NODE_BASE_INDEX, ROOT_NODE_BASE_INDEX, originDataSize - 1)
    }

    private fun update(index: Int, value: Int, node: Int, nodeLeftValue: Int, nodeRightValue: Int) {
        if (index !in nodeLeftValue..nodeRightValue) {
            return
        }

        if (nodeLeftValue == nodeRightValue) {
            tree[node] = value
            return
        }

        val mid = (nodeLeftValue + nodeRightValue) / 2
        val leftNode = node * 2 + 1
        val rightNode = leftNode + 1

        update(index, value, leftNode, nodeLeftValue, mid)
        update(index, value, rightNode, mid + 1, nodeRightValue)

        tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2]
    }

    private fun sumRange(left: Int, right: Int, node: Int, nodeLeftValue: Int, nodeRightValue: Int): Int {
        if (left > nodeRightValue || right < nodeLeftValue) {
            // if ((left..right).none { it in nodeLeftValue..nodeRightValue }) {
            return 0
        }

        if (left <= nodeLeftValue && nodeRightValue <= right) {
            // if ((nodeLeftValue..nodeRightValue).all { it in left..right }) {
            return tree[node]
        }

        val nodeMidValue = (nodeLeftValue + nodeRightValue) / 2
        val leftNode = node * 2 + 1
        val rightNode = leftNode + 1

        return sumRange(left, right, leftNode, nodeLeftValue, nodeMidValue) +
                sumRange(left, right, rightNode, nodeMidValue + 1, nodeRightValue)
    }

    companion object {
        const val ROOT_NODE_BASE_INDEX = 0
    }

}

package leetcode

@Suppress("UNUSED")
private fun sortedArrayToBST(nums: IntArray): TreeNode? {
    return sortedArrayToBST(0, nums.size, nums)
}

private fun sortedArrayToBST(startIndex: Int, endIndexExclusive: Int, nums: IntArray): TreeNode? {
    if (startIndex == endIndexExclusive) {
        return null
    }

    val rootIndex = (startIndex + endIndexExclusive) / 2
    val result = TreeNode(nums[rootIndex])
    result.left = sortedArrayToBST(startIndex, rootIndex, nums)
    result.right = sortedArrayToBST(rootIndex + 1, endIndexExclusive, nums)

    return result
}

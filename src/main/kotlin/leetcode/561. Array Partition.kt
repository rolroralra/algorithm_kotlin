package leetcode

private fun arrayPairSum(nums: IntArray): Int {
    return nums
        .sorted()
        .withIndex()
        .filter { it.index % 2 == 0 }
        .sumOf { it.value }
}

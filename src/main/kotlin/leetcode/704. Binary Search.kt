package leetcode

fun search(nums: IntArray, target: Int): Int {
    val result = nums.binarySearch(target)
    return if (result >= 0) result else -1
}
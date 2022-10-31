package leetcode.medium

fun main() {
    val array = intArrayOf(1, 1, 2)
    val count = removeDuplicates(array)
    println(count)
    println(array.slice(0 until count))
}

fun removeDuplicates(nums: IntArray): Int {
    val resultArray = nums.toSortedSet().toIntArray()

    for ((i, value) in resultArray.withIndex()) {
        nums[i] = value
    }

    return resultArray.size
}
package leetcode

fun main() {
    println(searchRange(intArrayOf(5,7,7,8,8,10), 8).contentToString())
    println(searchRange(intArrayOf(5,7,7,8,8,10), 6).contentToString())
    println(searchRange(intArrayOf(), 0).contentToString())

}

fun searchRange(nums: IntArray, target: Int): IntArray {
    if (target !in nums) {
        return intArrayOf(-1, -1)
    }

    return intArrayOf(lowerBound(nums, target), upperBound(nums, target))
}

fun lowerBound(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.lastIndex

    var lowerBound = nums.size
    while (start <= end) {
        val mid = (start + end) / 2

        if (nums[mid] >= target) {
            end = mid - 1
            lowerBound = mid
        } else {
            start = mid + 1
        }
    }
    return lowerBound
}

fun upperBound(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.lastIndex

    var upperBound = -1
    while (start <= end) {
        val mid = (start + end) / 2

        if (nums[mid] > target) {
            end = mid - 1
        } else {
            start = mid + 1
            upperBound = mid
        }
    }
    return upperBound
}
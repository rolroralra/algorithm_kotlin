package leetcode

import kotlin.math.max
import kotlin.math.min

fun main() {
    println(searchRange(intArrayOf(5,7,7,8,8,10), 8).contentToString())
    println(searchRange(intArrayOf(5,7,7,8,8,10), 6).contentToString())
    println(searchRange(intArrayOf(), 0).contentToString())

}

fun searchRange(nums: IntArray, target: Int): IntArray {
//    return intArrayOf(lowerBound(nums, target), upperBound(nums, target))
    val answer = intArrayOf(Int.MAX_VALUE, Int.MIN_VALUE)
    binarySearch(nums, target, answer = answer)

    if (answer[0] == Int.MIN_VALUE || answer[1] == Int.MIN_VALUE) {
        answer[0] = -1
        answer[1] = -1
    }

    return answer
}

private fun binarySearch(nums: IntArray, target: Int, start: Int = 0, end: Int = nums.lastIndex, answer: IntArray) {
    if (start > end) {
        return
    }

    val mid = (start + end) / 2
    if (nums[mid] == target) {
        answer[0] = min(answer[0], mid)
        answer[1] = max(answer[1], mid)

        binarySearch(nums, target, start ,mid - 1, answer)
        binarySearch(nums, target, mid + 1, end, answer)
    } else if (nums[mid] > target) {
        binarySearch(nums, target, start, mid - 1, answer)
    } else {
        binarySearch(nums, target, mid + 1, end, answer)
    }
}

private fun lowerBound(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.lastIndex

    var lowerBound = -1
    while (start <= end) {
        val mid = (start + end) / 2

        if (nums[mid] >= target) {
            end = mid - 1
        } else {
            start = mid + 1
        }

        if (nums[mid] ==  target) {
            lowerBound = mid
        }
    }
    return lowerBound
}

private fun upperBound(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.lastIndex

    var upperBound = -1
    while (start <= end) {
        val mid = (start + end) / 2

        if (nums[mid] > target) {
            end = mid - 1
        } else {
            start = mid + 1
        }

        if (nums[mid] ==  target) {
            upperBound = mid
        }
    }
    return upperBound
}

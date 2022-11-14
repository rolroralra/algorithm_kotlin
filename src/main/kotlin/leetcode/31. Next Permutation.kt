package leetcode

fun main() {
    nextPermutation(intArrayOf(3,2,1))
    nextPermutation(intArrayOf(1,1,5))
    nextPermutation(intArrayOf(2,2,7,5,4,3,2,2,1))
    nextPermutation(intArrayOf(1))
    nextPermutation(intArrayOf(1,3,2))
}

fun nextPermutation(nums: IntArray): Unit {
    var pivot = nums.size - 2

    while (pivot >= 0 && nums[pivot] >= nums[pivot + 1]) {
        pivot--
    }

    if (pivot >= 0) {
        var nextIndex = nums.size - 1
        while (nums[nextIndex] <= nums[pivot]) {
            nextIndex--
        }

        nums[pivot] = nums[nextIndex].also { nums[nextIndex] = nums[pivot] }
    }

    nums.reverse(pivot + 1, nums.size)

    println(nums.contentToString())
}

fun IntArray.reverse(startIndex: Int, endIndex: Int): Unit {
    val midPoint = (startIndex + endIndex) / 2
    if (startIndex == midPoint) return
    var reverseIndex = endIndex - 1
    for (index in startIndex until midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}


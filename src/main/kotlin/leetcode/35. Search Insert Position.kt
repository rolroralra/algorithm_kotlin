package leetcode

fun main() {
    println(searchInsert(intArrayOf(1,3,3,5,6), 3))
    println(searchInsert(intArrayOf(1,3,5,6), 5))
    println(searchInsert(intArrayOf(1,3,5,6), 2))
    println(searchInsert(intArrayOf(1,3,5,6), 7))

}

fun searchInsert(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.size - 1

    var lowerBound = nums.size
    while(start <= end) {
        val mid = (start + end) / 2

        if (nums[mid] < target) {
            start = mid + 1
        } else {
            lowerBound = mid
            end = mid - 1
        }
    }

    return lowerBound
}
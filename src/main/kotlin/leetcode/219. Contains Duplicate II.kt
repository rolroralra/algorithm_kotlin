package leetcode

import kotlin.math.abs

fun main() {
    check(containsNearbyDuplicate2(intArrayOf(1,2,3,1), 3))
    check(containsNearbyDuplicate2(intArrayOf(1,0,1,1), 1))
    check(!containsNearbyDuplicate2(intArrayOf(1,2,3,1,2,3), 2))
}

private fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    val indexMap = mutableMapOf<Int, MutableSet<Int>>()

    for ((index, num) in nums.withIndex()) {
        if (indexMap[num]?.any { abs(index - it) <= k } == true) {
            return true
        }

        indexMap.getOrPut(num) { mutableSetOf() }
            .add(index)
    }

    return false
}

private fun containsNearbyDuplicate2(nums: IntArray, k: Int): Boolean {
    val cacheSet = mutableSetOf<Int>()

    for ((index, value) in nums.withIndex()) {
        if (cacheSet.contains(value)) {
            return true
        }

        cacheSet.add(value)

        if (cacheSet.size > k) {
            cacheSet.remove(nums[index - k])
        }
    }

    return false
}

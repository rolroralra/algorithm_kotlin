package leetcode.medium

import java.util.*

fun main() {
    println(twoSum(intArrayOf(2,7,11,15), 9).joinToString(" "))
    println(twoSum(intArrayOf(3,2,4), 6).joinToString(" "))
    println(twoSum(intArrayOf(3, 3), 6).joinToString(" "))
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    val trackingNumberToIndexMap = HashMap<Int, Int>()

    for (index in nums.indices) {
        val targetRemain = target - nums[index]

        if (trackingNumberToIndexMap.contains(targetRemain)) {
            val secondIndex = trackingNumberToIndexMap[targetRemain]!!
            return intArrayOf(index, secondIndex).sortedArray()
        }

        trackingNumberToIndexMap[nums[index]] = index
    }

    return intArrayOf()
}

// Two Pointer Algorithm
fun twoSum2(nums: IntArray, target: Int): IntArray {
    val cloneNums = nums.clone()
    cloneNums.sort()

    var leftIndex = 0
    var rightIndex = cloneNums.size - 1

    while (leftIndex < rightIndex) {
        val sum = cloneNums[leftIndex] + cloneNums[rightIndex]
        if (sum == target) {
            break
        } else if (sum < target) {
            do {
                leftIndex++
            } while (leftIndex < rightIndex && cloneNums[leftIndex] == cloneNums[leftIndex - 1])
        } else {
            do {
                rightIndex--
            } while (leftIndex < rightIndex && cloneNums[rightIndex] == cloneNums[rightIndex + 1])
        }
    }

    return intArrayOf(nums.indexOf(cloneNums[leftIndex]), nums.lastIndexOf(cloneNums[rightIndex]))
}

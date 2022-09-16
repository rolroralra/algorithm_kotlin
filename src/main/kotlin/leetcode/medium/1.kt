package leetcode.medium

import java.util.*

fun main() {
    println(`1`().twoSum(intArrayOf(2,7,11,15), 9).joinToString(" "))
    println(`1`().twoSum(intArrayOf(3,2,4), 6).joinToString(" "))
    println(`1`().twoSum(intArrayOf(3, 3), 6).joinToString(" "))
}

class `1` {

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val indexMap = HashMap<Int, Int>()

        for (i in nums.indices) {
            val remainder = target - nums[i]

            if (indexMap.contains(remainder)) {
                val secondIndex = indexMap[remainder]!!
                return intArrayOf(i, secondIndex).sortedArray()
            }

            indexMap[nums[i]] = i
        }

        return intArrayOf()
    }

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
}
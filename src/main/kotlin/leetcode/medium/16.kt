package leetcode.medium

import kotlin.math.abs

fun main() {
    println(`16`().threeSumClosest(intArrayOf(-1,2,1,-4), 1))
    println(`16`().threeSumClosest(intArrayOf(0,0,0), 1))

}

class `16` {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()

        var minGap = Integer.MAX_VALUE
        var result = Integer.MAX_VALUE
        for (i in 0 until nums.size - 2) {
            val firstNumber = nums[i]
            val candidateResult = twoSumClosest(nums, target - firstNumber, i + 1) + firstNumber

            val currGap = abs(target - candidateResult)
            if (currGap == 0) {
                return candidateResult
            }

            if (minGap > currGap) {
                minGap = currGap
                result = candidateResult
            }
        }

        return result
    }

    private fun twoSumClosest(nums: IntArray, target: Int, startIndex: Int = 0, endIndex: Int = nums.size - 1): Int {
        var leftIndex = startIndex
        var rightIndex = endIndex

        var minGap = Integer.MAX_VALUE
        var result = Integer.MAX_VALUE
        while (leftIndex < rightIndex) {
            val sum = nums[leftIndex] + nums[rightIndex]
            val gap = Math.abs(sum - target)

            if (gap == 0) {
                return sum
            }

            if (minGap > gap) {
                minGap = gap
                result = sum
            }

            if (sum < target) {
                leftIndex++
            } else if (sum > target) {
                rightIndex--
            }
        }

        return result
    }
}
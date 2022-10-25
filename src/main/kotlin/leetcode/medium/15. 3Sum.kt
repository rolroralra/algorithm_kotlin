package leetcode.medium

import java.util.stream.Collectors

fun main() {
    println(`15`().threeSum(intArrayOf(-1,0,1,2,-1,-4,-2,-3,3,0,4)))
}

class `15` {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        nums.sort()

        for (i in nums.size - 1 downTo 2) {
            if (i < nums.size - 1 && nums[i] == nums[i + 1]) {
                continue
            }

            result.addAll(
                twoSum(nums, -nums[i], 0, i).stream()
                    .peek { it.add(nums[i]) }
                    .collect(Collectors.toList())
            )
        }

        return result
    }

    // Two Pointer Algorithm
    private fun twoSum(nums: IntArray, sum: Int = 0, startIndex: Int = 0, endIndex: Int = nums.size): List<MutableList<Int>> {
        val result = mutableListOf<MutableList<Int>>()

        var leftIndex = startIndex
        var rightIndex = endIndex - 1

        while (leftIndex < rightIndex) {
            val twoSum = nums[leftIndex] + nums[rightIndex]

            if (twoSum > sum) {
                rightIndex--
                continue
            } else if (twoSum < sum) {
                leftIndex++
                continue
            }

            result.add(mutableListOf(nums[leftIndex], nums[rightIndex]))

            do {
                leftIndex++
            } while(leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex - 1])

            do {
                rightIndex--
            } while(rightIndex > leftIndex && nums[rightIndex] == nums[rightIndex + 1])
        }

        return result
    }
}
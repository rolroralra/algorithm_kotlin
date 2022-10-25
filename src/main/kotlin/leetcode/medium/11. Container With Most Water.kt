package leetcode.medium

import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    println(Solution().maxArea(arrayOf(1,8,6,2,5,4,8,3,7)))
}

class Solution {
    // Two Pointer Algorithm
    fun maxArea(height: Array<Int>): Int {
        val pointers: Array<Int> = arrayOf(0, height.size - 1)

        var maxArea = 0

        for (i in 1..height.size) {
            val minHeight = min(height[pointers[0]], height[pointers[1]])

            maxArea = max(maxArea, minHeight * (pointers[1] - pointers[0]))

            if (height[pointers[0]] < height[pointers[1]]) {
                pointers[0]++
            } else {
                pointers[1]--
            }
        }

        return maxArea
    }
}

package leetcode

import kotlin.math.max

fun main() {
    println(longestConsecutive(intArrayOf(100,4,200,1,3,2)))
}

private fun longestConsecutive(nums: IntArray): Int {
    var answer = 0
    val numSet = nums.toSet()

    for (num in numSet) {
        if ((num - 1) !in numSet) {
            val candidateAnswer =
                generateSequence(num + 1) { it.plus(1) }
                    .takeWhile { it in numSet  }
                    .count() + 1

            answer = max(answer, candidateAnswer)
        }
    }

    return answer
}

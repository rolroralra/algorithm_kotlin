package baekjoon.binarysearch

import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()

    val inputs = br.readLine().split(" ").map { it.toInt() }

    val k = inputs[0]
    val n = inputs[1]

    val list = (1..k).map { br.readLine() }.map { it.toInt() }

    var start = 1L
    var end = list.maxOf { it.toLong() }
    var answer = -1L

    while (start <= end) {
        val candidate = (start + end) / 2

        if (isAnswer(candidate, n, list)) {
            answer = max(answer, candidate)
            start = candidate + 1
        } else {
            end = candidate - 1
        }
    }

    println(answer)
}

private fun isAnswer(candidate: Long, n: Int, list: List<Int>): Boolean {
    return list.sumOf { it.toLong() / candidate } >= n
}

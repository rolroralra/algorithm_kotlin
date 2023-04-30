package baekjoon.binarysearch

import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()
    val inputs = br.readLine().split(" ").map { it.toInt() }
    val n = inputs[0]
    val m = inputs[1]

    val list = br.readLine().split(" ").map { it.toInt() }

    var start = 0
    var end = list.maxOf { it }
    var answer = 0

    while (start <= end) {
        val candidate = (start + end) / 2

        if (isAnswer(candidate, m, list)) {
            answer = max(answer, candidate)

            start = candidate + 1
        } else {
            end = candidate - 1
        }
    }

    println(answer)
}

private fun isAnswer(candidate: Int, m: Int, list: List<Int>): Boolean {
    return list.filter { it > candidate }.sumOf { it.toLong() - candidate } >= m
}

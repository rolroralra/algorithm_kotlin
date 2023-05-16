package baekjoon.divideNconquer

import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val k = br.readLine().toInt()

    var minAnswer = 1L
    var maxAnswer = k.toLong()

    var answer = maxAnswer

    while (minAnswer <= maxAnswer) {
        val candidateAnswer = (minAnswer + maxAnswer) / 2

        if (getCount(candidateAnswer, n) >= k.toLong()) {
            maxAnswer = candidateAnswer - 1
            answer = min(answer, candidateAnswer)
        } else {
            minAnswer = candidateAnswer + 1
        }
    }

    println(answer)
}

private fun getCount(candidateAnswer: Long, n: Int): Long {
    return (1..n).sumOf { min(n.toLong(), candidateAnswer / it) }
}

package baekjoon.binarysearch

import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()

    val (n, c) = br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }

    val list = (1..n).map { br.readLine().toInt() }.sorted()

    var minAnswer = 1
    var maxAnswer = list.last() - list.first()
    var answer = minAnswer

    while (minAnswer <= maxAnswer) {
        val candidateAnswer = (minAnswer + maxAnswer) / 2

        if (isAnswer(candidateAnswer, list, c)) {
            minAnswer = candidateAnswer + 1
            answer = max(answer, candidateAnswer)
        } else {
            maxAnswer = candidateAnswer - 1
        }
    }

    println(answer)
}

private fun isAnswer(candidateAnswer: Int, list: List<Int>, count: Int): Boolean {
    var prevIndex = 0
    var currCount = 1

    for (i in 1..list.lastIndex) {
        if (list[i] - list[prevIndex] >= candidateAnswer) {
            prevIndex = i
            currCount++
        }
    }

    return currCount >= count
}

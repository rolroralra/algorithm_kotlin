package baekjoon.twopointer

import kotlin.math.abs

fun main() {
    val br = System.`in`.bufferedReader()

    val N = br.readLine().toInt()
    val list = br.readLine().split(" ").map { it.toInt() }.sorted()

    var startIndex = 0
    var endIndex = list.lastIndex

    var result = Int.MAX_VALUE
    val resultValues = IntArray(2)
    while (startIndex < endIndex) {
        val currValue = list[startIndex] + list[endIndex]
        val candidateValue = abs(currValue)

        if (candidateValue < result) {
            result = candidateValue
            resultValues[0] = list[startIndex]
            resultValues[1] = list[endIndex]
        }

        if (currValue == 0) {
            break
        } else if (currValue > 0) {
            endIndex--
        } else {
            startIndex++
        }
    }

    println("${resultValues[0]} ${resultValues[1]}")
}

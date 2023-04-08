package baekjoon.twopointer

fun main() {
    val br = System.`in`.bufferedReader()

    val input = br.readLine().split(" ")
    val N = input[0].toInt()
    val M = input[1].toInt()

    val list = br.readLine().split(" ").map { it.toInt() }

    var startIndex = 0
    var endIndex = 0

    var currSum = list[startIndex]
    var result = 0
    while (startIndex < N && endIndex < N) {
        if (currSum == M) {
            result++
            currSum -= list[startIndex]

            startIndex++
            endIndex++

            currSum += if (endIndex < N) list[endIndex] else 0
        } else if (currSum < M) {
            endIndex++

            currSum += if (endIndex < N) list[endIndex] else 0
        } else {
            currSum -= list[startIndex]

            startIndex++
        }
    }

    println(result)
}


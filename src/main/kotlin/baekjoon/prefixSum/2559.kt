package baekjoon.prefixSum

import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()
    val inputList  = br.readLine().trim().split(" ").map { it.toInt() }
    val n = inputList[0]
    val k = inputList[1]
    val list = br.readLine().trim().split(" ").map { it.toInt() }

    var sum = 0
    var result = Int.MIN_VALUE
    for (i in list.indices) {
        sum += list[i]

        if (i >= k - 1) {
            result = max(result, sum)
            sum -= list[i - k + 1]
        }
    }

    println(result)
}
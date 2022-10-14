package baekjoon.dp

import java.lang.Integer.max

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val inputList = br.readLine().trim().split(" ").map { it.toInt() }

    val dp = arrayOfNulls<Int>(n)

    var result = inputList[0]
    dp[0] = inputList[0]
    for (i in 1 until n) {
        dp[i] = max(dp[i - 1]!!.plus(inputList[i]), inputList[i])

        result = max(dp[i]!!, result)
    }

    println(result)
}
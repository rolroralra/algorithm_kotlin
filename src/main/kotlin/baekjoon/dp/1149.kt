package baekjoon.dp

import java.lang.Integer.min

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val rgbCount = 3
    val dp = Array(2) { Array(rgbCount) { 0 } }

    repeat(n) {
        val costList = br.readLine().trim().split(" ").map { it.toInt() }

        for (i in 0 until rgbCount) {
            val j = (i + 1) % rgbCount
            val k = (j + 1) % rgbCount

            dp[1][i] = min(dp[0][j], dp[0][k]) + costList[i]
        }

        for (i in 0 until rgbCount) {
            dp[0][i] = dp[1][i]
        }
    }

    println(dp[1].minOrNull() ?: 0)
}
package baekjoon.array

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    fun input() = br.readLine().trim()

    val (n, m) = input().split(" ").map { it.toInt() }
    val matrixA = Array(n) { input().split(" ").map { it.toInt() }.toIntArray() }
    val matrixB = Array(n) { input().split(" ").map { it.toInt() }.toIntArray() }

    for (i in matrixA.indices) {
        for (j in matrixA[i].indices) {
            matrixA[i][j] += matrixB[i][j]
        }
    }

    for (row in matrixA) {
        println(row.joinToString(" "))
    }
}

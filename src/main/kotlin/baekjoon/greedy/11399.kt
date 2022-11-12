package baekjoon.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    fun input() = br.readLine().trim()

    val n = input().toInt()

    var result = 0
    for ((i, value) in input().split(" ").map { it.toInt() }.sorted().withIndex()) {
        result += value * (n - i)
    }

    println(result)
}
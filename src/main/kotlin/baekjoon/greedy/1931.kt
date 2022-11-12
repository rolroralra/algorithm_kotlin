package baekjoon.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    fun input() = br.readLine().trim()

    val n = input().toInt()
    val list = (1..n)
        .map {
            input().split(" ")
                .map { it.toInt() } }
        .asSequence()
        .map { it[0] to it[1] }
        .sortedWith(
            Comparator.comparing<Pair<Int, Int>?, Int?> { it.second }
                .thenComparingInt{ it.first })
        .toList()

    var count = 0
    var lastTime = -1
    for (pair in list) {
        if (pair.first < lastTime) {
            continue
        }

        lastTime = pair.second
        count++
    }

    println(count)
}
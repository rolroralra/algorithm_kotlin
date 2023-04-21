package baekjoon.backtracking

import java.lang.StringBuilder

fun main() {
    val br = System.`in`.bufferedReader()

    while (true) {
        val input = br.readLine()

        if (input == null || input.isEmpty()) {
            break
        }

        val n = input.toInt()

        println(getCantorSetString((1..n).fold(1L) { acc, _ -> acc * 3L }))
    }
}

val cache = mutableMapOf(1L to "-", 3L to "- -")

private fun getCantorSetString(n: Long): String {
    if (cache.contains(n)) {
        return cache[n]!!
    }

    val nextN = n / 3

    val result = StringBuilder()

    val nextCantorSetString = getCantorSetString(nextN)

    result.append(nextCantorSetString)
        .append((1..nextN).joinToString("") { " " })
        .append(nextCantorSetString)

    cache[n] = result.toString()

    return cache[n]!!
}

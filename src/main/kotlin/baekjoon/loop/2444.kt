package baekjoon.loop

import kotlin.math.abs
import kotlin.text.StringBuilder

fun main() {
    val n = System.`in`.bufferedReader().readLine().toInt()

    val result = (1 until 2 * n)
        .map { abs(n - it) }.joinToString("\n") {
            StringBuilder().apply {
                (1..it).forEach { _ ->
                    append(" ")
                }

                (1 until 2 * (n - it)).forEach { _ ->
                    append("*")
                }
            }
        }

    print(result)
}

package baekjoon.stack

import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    (1..n).forEach { _ ->
        val input = br.readLine().trim()

        println(if (isVps(input)) "YES" else "NO")
    }

}

private fun isVps(input: String): Boolean {
    val stack = Stack<Char>()

    for (c in input.chars()) {
        when(c.toChar()) {
            '(' -> stack.push(c.toChar())
            ')' -> if (stack.isEmpty()) return false else stack.pop()
            else -> {
                return false
            }
        }
    }

    return stack.isEmpty()
}

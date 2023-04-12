package baekjoon.stack

import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()

    do {
        val input = br.readLine().trim().dropLast(1)

        if (input.isEmpty()) {
            break
        }

        println(if (isValid(input)) "yes" else "no")
    } while (input.isNotEmpty())
}

private fun isValid(input: String): Boolean {
    val stack = Stack<Char>()
    val map = mapOf('[' to ']', '(' to ')')

    for (c in input.toCharArray()) {
        when (c) {
            '[', '(' -> stack.push(c)
            ']', ')' -> {
                if (stack.isNotEmpty() && map[stack.peek()] == c) {
                    stack.pop()
                } else {
                    return false
                }
            }
        }
    }

    return stack.isEmpty()
}

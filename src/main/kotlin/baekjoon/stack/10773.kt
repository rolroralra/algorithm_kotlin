package baekjoon.stack

import java.util.Stack

fun main() {
    val stack = Stack<Long>()

    val br = System.`in`.bufferedReader()

    val k = br.readLine().toInt()

    (1..k).forEach { _ ->
        val input = br.readLine().toInt()

        if (input == 0) {
            stack.pop()
        } else {
            stack.add(input.toLong())
        }
    }

    println(stack.sum())
}

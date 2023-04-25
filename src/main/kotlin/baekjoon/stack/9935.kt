package baekjoon.stack

import java.util.Stack

fun main() {
    val br = System.`in`.bufferedReader()

    val input = br.readLine()
    val bomb = br.readLine()

    val stack = Stack<Char>()
    input.forEach { c ->
        stack.push(c)

        if (c == bomb.last() && stack.takeLast(bomb.length).joinToString("").equals(bomb)) {
            (1..bomb.length).forEach { _ -> stack.pop() }
        }
    }

    println(stack.joinToString("").ifEmpty { "FRULA" })
}

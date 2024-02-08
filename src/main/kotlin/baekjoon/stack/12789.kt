package baekjoon.stack

import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val stack = Stack<Int>()

    var index = 1
    br.readLine().split(" ").map { it.toInt() }.forEach { input ->
        while (stack.isNotEmpty() && stack.peek() == index) {
            index++
            stack.pop()
        }

        if (input == index) {
            index++
        } else {
            stack.push(input)
        }
    }

    while (stack.isNotEmpty() && stack.peek() == index) {
        index++
        stack.pop()
    }

    println(if (index > n) "Nice" else "Sad")
}

package baekjoon.stack

import java.util.*

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    var nextNumber = 1
    val stack = Stack<Int>()
    val result = mutableListOf<String>()
    (1..n).forEach { _ ->
        val inputNumber = br.readLine().toInt()

        while (true) {
            if (stack.isNotEmpty() && stack.peek() == inputNumber) {
                result.add("-")
                stack.pop()
                break
            } else if (nextNumber <= n) {
                stack.push(nextNumber++)
                result.add("+")
            } else {
                break
            }
        }
    }

    if (stack.isEmpty()) {
        println(
            result.joinToString("\n")
        )
    } else {
        println("NO")
    }
}

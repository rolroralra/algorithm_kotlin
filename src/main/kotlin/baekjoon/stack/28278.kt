package baekjoon.stack

import java.util.*
import kotlin.text.StringBuilder


fun main() {
    val br = System.`in`.bufferedReader(Charsets.UTF_8)

    val n = br.readLine().toInt()

    val stack = Stack<Int>()

    val sb = StringBuilder()
    repeat(n) {
        val inputs = br.readLine().split(" ").map { it.toInt() }

        when (inputs[0]) {
            1 -> stack.push(inputs[1])
            2 -> sb.append(if (stack.isEmpty()) -1 else stack.pop()).append("\n")
            3 -> sb.append(stack.size).append("\n")
            4 -> sb.append(if (stack.isEmpty()) 1 else 0).append("\n")
            5 -> sb.append(if (stack.isEmpty()) -1 else stack.peek()).append("\n")
        }
    }

    print(sb.toString())
}

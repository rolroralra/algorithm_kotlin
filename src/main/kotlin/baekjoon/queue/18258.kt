package baekjoon.queue

import java.util.LinkedList

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val queue = LinkedList<Int>()

    val result = mutableListOf<Int>()
    (1..n).forEach { _ ->
        val inputs = br.readLine().split(" ")

        when (inputs[0]) {
            "push" -> {
                queue.add(inputs[1].toInt())
            }
            "pop" -> {
                result.add(if (queue.isEmpty()) -1 else queue.poll())
            }
            "empty" -> {
                result.add(if (queue.isEmpty()) 1 else 0)
            }
            "size" -> {
                result.add(queue.size)
            }
            "front" -> {
                result.add(if (queue.isEmpty()) -1 else queue.peek())

            }
            "back" -> {
                result.add(if (queue.isEmpty()) -1 else queue.peekLast())
            }
        }
    }

    println(result.joinToString("\n"))
}

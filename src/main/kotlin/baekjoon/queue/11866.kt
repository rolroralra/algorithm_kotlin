package baekjoon.queue

import java.util.LinkedList

fun main() {

    val br = System.`in`.bufferedReader()

    val inputs = br.readLine().split(" ").map { it.toInt() }

    val queue = LinkedList<Int>()
    (1..inputs[0]).forEach(queue::add)

    val result = mutableListOf<Int>()

    (1 until inputs[0]).forEach { _ ->
        (1 until inputs[1]).forEach {
            queue.add(queue.poll())
        }

        result.add(queue.poll())
    }

    result.add(queue.poll())

    println(result.joinToString(", ", "<", ">"))
}

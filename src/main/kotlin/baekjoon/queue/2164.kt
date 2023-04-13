package baekjoon.queue

import java.util.LinkedList

fun main() {

    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val queue = LinkedList<Int>()

    (1..n).forEach { queue.add(it) }

    while (queue.size >= 2) {
        queue.poll()
        queue.add(queue.poll())
    }

    println(queue.poll())
}

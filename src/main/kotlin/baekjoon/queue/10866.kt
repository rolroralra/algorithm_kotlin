package baekjoon.queue

import java.util.LinkedList

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val deque = LinkedList<String>()

    (1..n).forEach { _ ->
        val inputs = br.readLine().split(" ")

        when (inputs[0]) {
            "push_back" -> {
                deque.addLast(inputs[1])
            }
            "push_front" -> {
                deque.addFirst(inputs[1])
            }
            "pop_front" -> {
                println(if (deque.isNotEmpty()) deque.pollFirst() else -1)
            }
            "pop_back" -> {
                println(if (deque.isNotEmpty()) deque.pollLast() else -1)
            }
            "size" -> {
                println(deque.size)
            }
            "empty" -> {
                println(if (deque.isEmpty()) 1 else 0)
            }
            "front" -> {
                println(if (deque.isNotEmpty()) deque.peekFirst() else -1)
            }
            "back" -> {
                println(if (deque.isNotEmpty()) deque.peekLast() else -1)
            }
        }
    }
}

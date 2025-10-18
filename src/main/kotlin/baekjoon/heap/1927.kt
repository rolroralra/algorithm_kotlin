package baekjoon.heap

import algorithm.heap.Heap

fun main() {
    val n = readln().trim().toInt()

    val heap = Heap<Int>()

    for (i in 0 until n) {
        when (val input = readln().trim().toInt()) {
            0 -> println(if (heap.isEmpty()) 0 else heap.poll())
            else -> heap.add(input)
        }
    }
}
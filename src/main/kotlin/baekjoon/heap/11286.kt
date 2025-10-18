package baekjoon.heap

import algorithm.heap.Heap
import kotlin.math.abs

fun main() {
    val n = readln().trim().toInt()

    val heap =
        Heap<Int>(comparator = { a: Int, b: Int -> if (abs(a) == abs(b)) a - b else abs(a) - abs(b) })
    for (i in 0 until n) {
        when (val input = readln().trim().toInt()) {
            0 -> println(if (heap.isEmpty()) 0 else heap.poll())
            else -> heap.add(input)
        }
    }
}
package baekjoon.dp

import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val list = mutableListOf<MutableList<Int>>()

    (1..n).forEach { _ ->
        list.add(br.readLine().split(" ").map { it.toInt() }.toMutableList())
    }

    (1 until n).forEach { i ->
        (0..i).forEach { j ->
            list[i][j] += max(list[i - 1].getOrElse(j - 1) { 0 }, list[i - 1].getOrElse(j) { 0 })
        }
    }

    println(list[n - 1].maxOf { it })
}

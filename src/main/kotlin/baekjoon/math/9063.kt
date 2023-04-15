package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val pairList = (1..n).map { _ ->
        val inputs = br.readLine().split(" ").map { it.toInt() }
        inputs[0] to inputs[1]
    }

    val height = pairList.maxOf { it.first } - pairList.minOf { it.first }
    val width = pairList.maxOf { it.second } - pairList.minOf { it.second }

    println(height * width)
}

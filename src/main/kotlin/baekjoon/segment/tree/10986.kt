package baekjoon.segment.tree

fun main() {
    val br = System.`in`.bufferedReader()
    val input = br.readLine().split(" ").map { it.toInt() }

    val n = input[0]
    val m = input[1]

    val arr = br.readLine().split(" ").map { it.toInt() }

    val rangeSum = mutableListOf(*arr.toTypedArray())

    rangeSum[0] %= m

    rangeSum.indices.filter { it > 0 }.forEach { i ->
        rangeSum[i] = (rangeSum[i] + rangeSum[i - 1]) % m
    }

    val countMap = rangeSum.groupingBy { it }.eachCount()

    val result = countMap.values.filter { it > 1 }.sumOf { it.toLong() * (it - 1) / 2 } +  (countMap[0] ?: 0)

    println(result)
}

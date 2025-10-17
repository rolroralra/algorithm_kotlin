package baekjoon.prefixSum

import kotlin.math.min


fun main() {
    System.`in`.bufferedReader()
    val br = System.`in`.bufferedReader()
    val (N, M, K) = br.readLine().trim().split(" ").map { it.toInt() }.toTriple()

    val graph = (1..N).map { br.readLine().trim() }

    val prefixSumList = List(graph.size) { MutableList(graph[0].length) { 0 } }

    println(calculateResult(K, graph, prefixSumList))
}

private fun calculateResult(k: Int, graph: List<String>, prefixSumList: List<MutableList<Int>>): Int {

    return min(calculateResult(k, "W", graph, prefixSumList), calculateResult(k, "B", graph, prefixSumList))
}

private fun calculateResult(
    k: Int, color: String, graph: List<String>,
    prefixSumList: List<MutableList<Int>>): Int {

    loadingPrefixSumList(graph, color, prefixSumList)

    var result = Int.MAX_VALUE
    for (row in (0..graph.lastIndex - k + 1)) {
        for (col in (0..graph[0].lastIndex - k + 1)) {
            result = min(result, calculateResult(row, col, k, prefixSumList))
        }
    }
    return result
}

private fun calculateResult(row: Int, col: Int, k: Int, prefixSumList: List<List<Int>>): Int {
    var result = prefixSumList[row + k - 1][col + k - 1]

    val isExistsPreviousRow = row > 0
    val isExistsPreviousCol = col > 0

    if (isExistsPreviousRow) {
        result -= prefixSumList[row - 1][col + k - 1]
    }

    if (isExistsPreviousCol) {
        result -= prefixSumList[row + k - 1][col - 1]
    }

    if (isExistsPreviousRow && isExistsPreviousCol) {
        result += prefixSumList[row - 1][col - 1]
    }

    return result
}

private fun loadingPrefixSumList(
    graph: List<String>,
    color: String,
    prefixSumList: List<MutableList<Int>>
): List<List<Int>> {

    for (row in graph.indices) {
        for (col in graph[0].indices) {
            val shouldBeSameColor = (row + col) % 2 == 0

            if (shouldBeSameColor) {
                prefixSumList[row][col] += if (graph[row][col] == color[0]) 0 else 1
            } else {
                prefixSumList[row][col] += if (graph[row][col] != color[0]) 0 else 1
            }

            val isExistsPreviousRow = row > 0
            val isExistsPreviousCol = col > 0

            if (isExistsPreviousRow) {
                prefixSumList[row][col] += prefixSumList[row - 1][col]
            }

            if (isExistsPreviousCol) {
                prefixSumList[row][col] += prefixSumList[row][col - 1]
            }

            if (isExistsPreviousRow && isExistsPreviousCol) {
                prefixSumList[row][col] -= prefixSumList[row - 1][col - 1]
            }
        }
    }

    return prefixSumList
}


private fun <T> List<T>.toTriple(): Triple<T, T, T> {
    return Triple(this[0], this[1], this[2])
}

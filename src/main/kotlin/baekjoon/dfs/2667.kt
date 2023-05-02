package baekjoon.dfs

import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val graph = (1..n).map { br.readLine().asSequence().map { it.code == '1'.code }.toMutableList() }

    val resultList = mutableListOf<Int>()
    var result = 0

    repeat(n) { i ->
        repeat(n) { j ->
            if (graph[i][j]) {
                result++
                resultList.add(dfs(i, j, graph))
            }
        }
    }

    println(result)
    resultList.sorted().forEach { println(it) }
}

private fun dfs(row: Int, col: Int, graph: List<MutableList<Boolean>>): Int {
    val n = graph.size

    val stack = Stack<Int>()
    stack.push(indexValueOf(row, col, n))
    graph[row][col] = false

    var result = 0
    while (stack.isNotEmpty()) {
        val currIndexValue = stack.pop()
        result++

        for ((nextRow, nextCol) in nextIndexValues(currIndexValue, n)) {
            if (!graph[nextRow][nextCol]) {
                continue
            }

            graph[nextRow][nextCol] = false
            stack.add(indexValueOf(nextRow, nextCol, n))
        }
    }

    return result
}

private fun indexValueOf(row: Int, col: Int, n: Int): Int {
    return row * n + col
}

private fun convertToPair(indexValue: Int, n: Int): Pair<Int, Int> {
    return indexValue / n to indexValue % n
}

private fun nextIndexValues(indexValue: Int, n: Int): List<Pair<Int, Int>> {
    val (row, col) = convertToPair(indexValue, n)

    val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

    return directions.map { row + it.first to col + it.second }
        .filter { it.first in (0 until n) }
        .filter { it.second in (0 until n) }
}

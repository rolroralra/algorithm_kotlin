package baekjoon.bfs

import java.util.LinkedList

fun main() {
    val br = System.`in`.bufferedReader()
    val input = br.readLine().split(" ").map { it.toInt() }

    val n = input[0]
    val m = input[1]

    val graph = (1..n).map { br.readLine().asSequence().map { it.code - '0'.code }.toMutableList() }

    bfs(0, 0, graph)

    println(-graph[n - 1][m - 1])
}

private fun bfs(row: Int, col: Int, graph: List<MutableList<Int>>) {
    val queue = LinkedList<Pair<Int, Int>>()

    queue.add(row to col)
    graph[row][col] = -1

    while (queue.isNotEmpty()) {
        val currPoint = queue.poll()

        for (nextPoint in nextPoints(currPoint, graph.size, graph[0].size)) {
            if (graph[nextPoint.first][nextPoint.second] <= 0) {
                continue
            }

            graph[nextPoint.first][nextPoint.second] = graph[currPoint.first][currPoint.second] - 1
            queue.add(nextPoint)
        }
    }
}

private fun nextPoints(point: Pair<Int, Int>, rowSize: Int, colSize: Int): List<Pair<Int, Int>> {
    return listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
        .map { point.first + it.first to point.second + it.second }
        .filter { it.first in (0 until rowSize) }
        .filter { it.second in (0 until colSize) }
}

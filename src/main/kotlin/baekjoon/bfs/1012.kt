package baekjoon.bfs

import java.util.LinkedList

fun main() {
    val br = System.`in`.bufferedReader()
    val testCaseCount = br.readLine().toInt()

    repeat(testCaseCount) {
        val input = br.readLine().split(" ").map { it.toInt() }
        val m = input[0]
        val n = input[1]
        val k = input[2]

        val graph = List(m) { MutableList(n) { false } }

        val points = (1..k).map {
            br.readLine().split(" ")
                .map { it.toInt() }
                .let {
                    graph[it[0]][it[1]] = true
                    it[0] to it[1]
                }
        }

        var result = 0

        points.forEach {
            if (graph[it.first][it.second]) {
                bfs(it, graph)
                result++
            }
        }

        println(result)
    }
}

private fun bfs(point: Pair<Int, Int>, graph: List<MutableList<Boolean>>) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(point)
    graph[point.first][point.second] = false

    while (queue.isNotEmpty()) {
        val currPoint = queue.poll()

        for (nextPoint in nextPoints(currPoint, graph.size, graph[0].size)) {
            if (!graph[nextPoint.first][nextPoint.second]) {
                continue
            }

            graph[nextPoint.first][nextPoint.second] = false
            queue.add(nextPoint)
        }
    }
}

private fun nextPoints(point: Pair<Int, Int>, rowSize: Int, colSize: Int): List<Pair<Int, Int>> {
    return listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        .map { point.first + it.first to point.second + it.second }
        .filter { it.first in (0 until rowSize) }
        .filter { it.second in (0 until colSize) }
}

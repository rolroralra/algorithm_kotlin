package baekjoon.bfs

import java.util.LinkedList

fun main() {
    val br = System.`in`.bufferedReader()

    val testCaseCount = br.readLine().toInt()

    repeat(testCaseCount) {
        val n = br.readLine().toInt()

        val startPoint = br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }
        val endPoint = br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }

        println(bfs(startPoint, endPoint, n))
    }
}

private fun bfs(startPoint: Pair<Int, Int>, endPoint: Pair<Int, Int>, n: Int): Int {
    val visit = List(n) { MutableList(n) { -1 } }

    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(startPoint)
    visit[startPoint.first][startPoint.second] = 0

    while (queue.isNotEmpty()) {
        val currPoint = queue.poll()

        if (currPoint.first == endPoint.first && currPoint.second == endPoint.second) {
            return visit[currPoint.first][currPoint.second]
        }

        for (nextPoint in nextPoints(currPoint, n)) {
            if (visit[nextPoint.first][nextPoint.second] >= 0) {
                continue
            }

            queue.add(nextPoint)
            visit[nextPoint.first][nextPoint.second] = visit[currPoint.first][currPoint.second] + 1
        }
    }

    return -1
}

private fun nextPoints(point: Pair<Int, Int>, n: Int): List<Pair<Int, Int>> {
    return listOf(1 to 2, 1 to -2, -1 to 2, -1 to -2, 2 to 1, 2 to -1, -2 to 1, -2 to -1)
        .map { point.first + it.first to point.second + it.second }
        .filter { it.first in (0 until n) }
        .filter { it.second in (0 until n) }
}

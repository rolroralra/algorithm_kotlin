package baekjoon.bfs

import java.util.LinkedList

fun main() {
    val br = System.`in`.bufferedReader()

    val (m, n) = br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }

    val graph = (1..n).map { br.readLine().split(" ").map { it.toInt() - 1 }.toMutableList() }

    val queue = LinkedList<Pair<Int, Int>>()
    repeat(n) { i ->
        repeat(m) { j ->
            if (graph[i][j] == 0) {
                queue.add(i to j)
            }
        }
    }

    while (queue.isNotEmpty()) {
        val currPoint = queue.poll()

        for (nextPoint in currPoint.nextPoints(n, m)) {
            if (graph[nextPoint.first][nextPoint.second] != -1) {
                continue
            }

            queue.add(nextPoint)
            graph[nextPoint.first][nextPoint.second] = graph[currPoint.first][currPoint.second] + 1
        }
    }

    println(if (graph.any { list -> list.any { it == -1 } }) -1 else graph.maxOf { list -> list.maxOf { it } })
}

private fun Pair<Int, Int>.nextPoints(n: Int, m: Int): List<Pair<Int, Int>> {
    return listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        .map { first + it.first to second + it.second }
        .filter { it.first in (0 until n) }
        .filter { it.second in (0 until m) }
}

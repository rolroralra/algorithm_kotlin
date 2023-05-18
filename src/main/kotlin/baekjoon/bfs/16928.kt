package baekjoon.bfs

import java.util.LinkedList

fun main() {
    val br = System.`in`.bufferedReader()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }

    val graph = (1..n + m).associate { br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] } }

    val isVisited = MutableList(101) { false }

    var answer = 100
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(1 to 0)
    isVisited[1] = true

    while (queue.isNotEmpty()) {
        val (currIndex, currCount) = queue.poll()

        if (currIndex == 100) {
            answer = currCount
            break
        }

        currIndex.nextIndex()
            .filterNot { isVisited[it] }
            .forEach { nextIndex ->
                isVisited[nextIndex] = true

                if (graph.containsKey(nextIndex)) {
                    if (!isVisited[graph[nextIndex]!!]) {
                        queue.add(graph[nextIndex]!! to currCount + 1)
                        isVisited[graph[nextIndex]!!] = true
                    }
                } else {
                    queue.add(nextIndex to currCount + 1)
                }
            }
    }

    println(answer)
}

private fun Int.nextIndex(): List<Int> = (1..6).map { it + this }.filter { it in 1..100 }

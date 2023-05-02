package baekjoon.bfs

import java.util.*

fun main() {

    val br = System.`in`.bufferedReader()

    val v = br.readLine().toInt()
    val e = br.readLine().toInt()

    val graph = Array<MutableList<Int>>(v) { mutableListOf() }
    repeat(e) {
        val input = br.readLine().split(" ").map { it.toInt() - 1 }

        graph[input[0]].add(input[1])
        graph[input[1]].add(input[0])
    }

    val isVisited = MutableList(v) { false }
    isVisited[0] = true

    val queue = LinkedList<Int>()
    queue.add(0)

    var result = 0

    while (queue.isNotEmpty()) {
        val currIndex = queue.poll()
        result++

        for (nextIndex in graph[currIndex]) {
            if (isVisited[nextIndex]) {
                continue
            }

            isVisited[nextIndex] = true
            queue.add(nextIndex)
        }
    }

    println(result - 1)
}

package baekjoon.bfs

fun main() {
    val inputList = readln().trim().split(" ").map { it.toInt() }

    val n = inputList[0]
    val m = inputList[1]
    val r = inputList[2] - 1

    val graph = MutableList<MutableList<Int>>(n) { mutableListOf() }

    repeat(m) {
        val inputs = readln().trim().split(" ").map { it.toInt() - 1 }
        val u = inputs[0]
        val v = inputs[1]

        graph[u].add(v)
        graph[v].add(u)
    }

    graph.forEach { it.sortWith(Comparator.reverseOrder()) }

    val isVisited = (0 until n).map { 0 }.toMutableList()
    val queue = java.util.LinkedList<Int>()

    var visitIndex = 0
    isVisited[r] = ++visitIndex
    queue.add(r)
    while (queue.isNotEmpty()) {
        val currIndex = queue.poll()

        for (nextIndex in graph[currIndex]) {
            if (isVisited[nextIndex] > 0) {
                continue
            }

            isVisited[nextIndex] = ++visitIndex
            queue.add(nextIndex)
        }
    }

    isVisited.forEach{ println(it) }
}


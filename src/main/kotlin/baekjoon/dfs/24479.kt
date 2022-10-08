package baekjoon.dfs

fun main() {
    val list = readln().trim().split(" ").map { it.toInt() }
    val n = list[0]
    val m = list[1]
    val r = list[2]

    val graph = mutableMapOf<Int, MutableList<Int>>()
    for (i in 0 until m) {
        val inputList = readln().trim().split(" ").map { it.toInt() - 1 }

        graph.putIfAbsent(inputList[0], mutableListOf())
        graph[inputList[0]]?.add(inputList[1])
        graph.putIfAbsent(inputList[1], mutableListOf())
        graph[inputList[1]]?.add(inputList[0])
    }

    graph.forEach { (_: Int, l: MutableList<Int>) -> l.sort() }

    val isVisited = (1..n).map { 0 }.toMutableList()
    dfs( r - 1, graph, isVisited)

    isVisited.forEach{ println(it) }
}

var count = 1

fun dfs(index: Int, graph: Map<Int, List<Int>>, isVisited: MutableList<Int>) {
    isVisited[index] = count++

    for (nextIndex in graph.getOrDefault(index, emptyList())) {
        if (isVisited[nextIndex] > 0) {
            continue
        }

        dfs(nextIndex, graph, isVisited)
    }
}
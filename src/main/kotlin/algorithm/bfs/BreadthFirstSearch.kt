package algorithm.bfs

import java.util.LinkedList


inline fun <reified T> bfs(
    graph: List<List<T>>,
    startIndex: Int = 0,
    noinline visitProcess: (Int) -> Unit = {},
) {
    when (T::class) {
        Int::class -> {
            require(graph.all { row ->
                row.all { it is Int }
            })

            @Suppress("UNCHECKED_CAST")
            bfsWithAdjacentMatrix(
                graph as List<List<Int>>,
                startIndex,
                visitProcess,
            )
        }
        Pair::class -> {
            require(graph.all { row ->
                row.all { it is Pair<*, *> }
            })

            @Suppress("UNCHECKED_CAST")
            bfsWithAdjacentList(graph as List<List<Pair<Int, Int>>>, startIndex, visitProcess)
        }
        else -> {
            throw IllegalArgumentException("Unsupported type: ${T::class}")
        }
    }
}

fun bfsWithAdjacentList(
    adjacentList: List<List<Pair<Int, Int>>>,
    startIndex: Int = 0,
    visitProcess: (Int) -> Unit = {},
) {
    require(startIndex in adjacentList.indices) {
        "startIndex must be in range [0, ${adjacentList.size})"
    }
    require(adjacentList.isNotEmpty()) { "At least one adjacent is empty" }

    val isVisited = MutableList(adjacentList.size) { false }

    val queue = LinkedList<Int>()
    queue.add(startIndex)
    isVisited[startIndex] = true

    while (queue.isNotEmpty()) {
        val currIndex = queue.pollFirst()

        visitProcess(currIndex)

        @Suppress("UNUSED_VARIABLE")
        for ((nextIndex, edgeLength) in adjacentList[currIndex]) {
            if (isVisited[nextIndex]) {
                continue
            }

            queue.add(nextIndex)
            isVisited[nextIndex] = true
        }
    }
}

fun bfsWithAdjacentMatrix(
    adjacentMatrix: List<List<Int>>,
    startIndex: Int,
    visitProcess: (Int) -> Unit = {}
) {
    require(startIndex in adjacentMatrix.indices) {
        "startIndex must be in range [0, ${adjacentMatrix.size})"
    }
    require(adjacentMatrix.isNotEmpty() &&
            adjacentMatrix.all { it.size == adjacentMatrix.size }) {
        "adjacent matrix must be non-empty square matrix"
    }

    val isVisited = MutableList(adjacentMatrix.size) { false }

    val queue = LinkedList<Int>()
    queue.add(startIndex)
    isVisited[startIndex] = true

    while (queue.isNotEmpty()) {
        val currIndex = queue.pollFirst()

        visitProcess(currIndex)

        for (nextIndex in adjacentMatrix[currIndex].indices) {
            if (adjacentMatrix[currIndex][nextIndex] == 0 || isVisited[nextIndex]) {
                continue
            }

            queue.add(nextIndex)
            isVisited[nextIndex] = true
        }
    }
}

fun main() {
    val adjMatrix = listOf(
        listOf(0, 1, 1, 0, 0),
        listOf(1, 0, 0, 1, 0),
        listOf(1, 0, 0, 0, 1),
        listOf(0, 1, 0, 0, 0),
        listOf(0, 0, 1, 0, 0)
    )

    bfs(adjMatrix, 0, { println("$it visited") })
    println()

    val adjList = listOf(
        listOf(1 to 1, 2 to 1),
        listOf(0 to 1, 3 to 1),
        listOf(0 to 1, 4 to 1),
        listOf(1 to 1),
        listOf(2 to 1)
    )

    bfs(adjList, 0, { println("$it visited") })
    println()
}
package algorithm.topologicalsort

import java.util.LinkedList
import java.util.Stack

/**
 * @param adjacencyList Adjacency List
 *
 * @return Pair of (topological order, hasCycle). When hasCycle is true, the order is undefined.
 */
fun topologicalSortByDfs(adjacencyList: List<List<Int>>): Pair<List<Int>, Boolean> {
    val isVisited = MutableList(adjacencyList.size) { false }
    val isFinished = MutableList(adjacencyList.size) { false }
    val stack = Stack<Int>()
    var hasCycle = false

    fun dfs(currIndex: Int) {
        if (isVisited[currIndex]) {
            return
        }

        isVisited[currIndex] = true

        for (nextIndex in adjacencyList[currIndex]) {
            if (!isVisited[nextIndex]) {
                dfs(nextIndex)

            } else if (!isFinished[nextIndex]) {
                hasCycle = true
            }
        }

        isFinished[currIndex] = true
        stack.push(currIndex)
    }

    IntRange(0, adjacencyList.size - 1).filterNot { isVisited[it] }.forEach {
        dfs(it)
    }

    return stack.reversed() to hasCycle
}

/**
 * @param adjacencyList Adjacency List
 *
 * @return Pair of (topological order, hasCycle). When hasCycle is true, the order is undefined.
 */
fun topologicalSortByBfsWithIndegree(adjacencyList: List<List<Int>>): Pair<List<Int>, Boolean> {
    val result = mutableListOf<Int>()
    val inDegree = MutableList(adjacencyList.size) { 0 }

    // Initialize inDegree of graph
    for (toIndices in adjacencyList) {
        for (toIndex in toIndices) {
            inDegree[toIndex]++
        }
    }

    // BFS with InDegree
    val queue = LinkedList<Int>()

    for (index in adjacencyList.indices) {
        // start BFS at index having indegree zero value
        if (inDegree[index] == 0) {
            queue.add(index)
        }
    }

    while (queue.isNotEmpty()) {
        val currIndex = queue.pop()

        result.add(currIndex)

        for (nextIndex in adjacencyList[currIndex]) {
            // settlement inDegree from currIndex
            inDegree[nextIndex]--

            if (inDegree[nextIndex] == 0) {
                queue.add(nextIndex)
            }
        }
    }

    return result to (result.size != adjacencyList.size)
}

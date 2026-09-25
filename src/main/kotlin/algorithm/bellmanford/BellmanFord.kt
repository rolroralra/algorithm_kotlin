package algorithm.bellmanford

import java.util.Stack

/**
 * @return Triple of (distance list, previous-index list for path reconstruction, has-negative-cycle)
 */
fun bellmanFord(edges: List<Triple<Int, Int, Int>>, startIndex: Int, vertexSize: Int): Triple<List<Long>, List<Int>, Boolean> {
    val distance = MutableList(vertexSize) { Long.MAX_VALUE }
    val prevIndex = MutableList(vertexSize) { -1 }

    distance[startIndex] = 0

    IntRange(0, edges.size - 1).forEach { _ ->
        for ((fromIndex, toIndex, edgeLength) in edges) {
            if (distance[fromIndex] == Long.MAX_VALUE) {
                continue
            }

            val candidateDistance = distance[fromIndex] + edgeLength
            if (distance[toIndex] > candidateDistance) {
                distance[toIndex] = candidateDistance
                prevIndex[toIndex] = fromIndex
            }
        }
    }

    // Check existence of negative cycle in graph
    for ((fromIndex, toIndex, edgeLength) in edges) {
        if (distance[fromIndex] == Long.MAX_VALUE) {
            continue
        }

        val candidateDistance = distance[fromIndex] + edgeLength
        if (distance[toIndex] > candidateDistance) {
            return Triple(distance, prevIndex, true)
        }
    }

    return Triple(distance, prevIndex, false)
}

fun shortestPath(prevIndex: List<Int>, targetIndex: Int): List<Int> {
    val stack = Stack<Int>()

    var currIndex = targetIndex
    while (currIndex >= 0) {
        stack.push(currIndex)
        currIndex = prevIndex[currIndex]
    }

    return stack.toList().reversed()
}

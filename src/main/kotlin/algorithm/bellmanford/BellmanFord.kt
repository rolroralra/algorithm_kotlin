package algorithm.bellmanford

import java.util.Stack

/**
 * @param edges List of edges represented as Triple(fromIndex, toIndex, edgeLength)
 * @param startIndex Index of the starting vertex
 * @param vertexSize Number of vertices in the graph
 *
 * @return Triple of (distance list, previous-index list for path reconstruction, has-negative-cycle)
 */
fun bellmanFord(edges: List<Triple<Int, Int, Int>>, startIndex: Int, vertexSize: Int): Triple<List<Long>, List<Int>, Boolean> {
    val distance = MutableList(vertexSize) { Long.MAX_VALUE }
    val prevIndex = MutableList(vertexSize) { -1 }

    distance[startIndex] = 0

    // Relax edges repeatedly V-1 times (where V is the number of vertices)
    IntRange(0, vertexSize - 2).forEach { _ ->
        for ((fromIndex, toIndex, edgeLength) in edges) {
            if (distance[fromIndex] == Long.MAX_VALUE) {
                continue
            }

            // Update distance and prevIndex if a shorter path is found
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

        // If we can still relax an edge, then there is a negative cycle
        val candidateDistance = distance[fromIndex] + edgeLength
        if (distance[toIndex] > candidateDistance) {
            return Triple(distance, prevIndex, true)
        }
    }

    return Triple(distance, prevIndex, false)
}

/**
 * @param prevIndex List of previous indices for path reconstruction
 * @param targetIndex Index of the target vertex
 *
 * @return List of vertex indices representing the shortest path from the start vertex to the target vertex
 */
fun shortestPath(prevIndex: List<Int>, targetIndex: Int): List<Int> {
    val stack = Stack<Int>()

    var currIndex = targetIndex
    while (currIndex >= 0) {
        stack.push(currIndex)
        currIndex = prevIndex[currIndex]
    }

    return stack.toList().reversed()
}

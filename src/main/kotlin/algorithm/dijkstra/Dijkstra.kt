package algorithm.dijkstra

import algorithm.heap.Heap
import java.util.PriorityQueue
import java.util.Stack
import kotlin.to

/**
 * @param graph Adjacency List for graph
 * @param startIndex source index
 *
 * @return Pair of (distance list, previous-index list) satisfying shortest path
 */
fun dijkstraByPriorityQueue(graph: List<List<Pair<Int, Int>>>, startIndex: Int): Pair<List<Long>, List<Int>> {
    val isVisited = MutableList(graph.size) { false }
    val prevIndex = MutableList(graph.size) { -1 }
    val distance = MutableList(graph.size) { Long.MAX_VALUE }

    val queue = PriorityQueue<Pair<Int, Long>>(Comparator.comparing { it.second })

    distance[startIndex] = 0
    queue.add(startIndex to 0)

    while (queue.isNotEmpty()) {
        val (currIndex, _) = queue.poll()

        if (isVisited[currIndex]) {
            continue
        }

        isVisited[currIndex] = true

        for ((nextIndex, edgeLength) in graph[currIndex]) {
            val nextDistanceCandidate = distance[currIndex] + edgeLength

            if (distance[nextIndex] > nextDistanceCandidate) {
                distance[nextIndex] = nextDistanceCandidate
                prevIndex[nextIndex] = currIndex

                queue.add(nextIndex to nextDistanceCandidate)
            }
        }
    }

    return distance to prevIndex

}

/**
 *  @param graph Adjacency List for graph
 *  @param startIndex source index
 *
 * @return Pair of (distance list, previous-index list) satisfying shortest path
 */
fun dijkstraByHeap(graph: List<List<Pair<Int, Int>>>, startIndex: Int): Pair<List<Long>, List<Int>> {
    val isVisited = MutableList(graph.size) { false }
    val prevIndex = MutableList(graph.size) { -1 }
    val distance = MutableList(graph.size) { Long.MAX_VALUE }

    val queue = Heap<IndexWithDistance>()

    distance[startIndex] = 0
    queue.add(IndexWithDistance(startIndex, 0))

    while (queue.isNotEmpty()) {
        val (currIndex, _) = queue.poll()

        if (isVisited[currIndex]) {
            continue
        }

        isVisited[currIndex] = true

        for ((nextIndex, edgeLength) in graph[currIndex]) {
            val nextDistanceCandidate = distance[currIndex] + edgeLength
            if (distance[nextIndex] > nextDistanceCandidate) {
                distance[nextIndex] = nextDistanceCandidate
                prevIndex[nextIndex] = currIndex

                queue.add(IndexWithDistance(nextIndex, nextDistanceCandidate))
            }
        }
    }

    return distance to prevIndex
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

private data class IndexWithDistance(val index: Int, val distance: Long) : Comparable<IndexWithDistance> {
    override fun compareTo(other: IndexWithDistance): Int = distance.compareTo(other.distance)
}

package algorithm.bellmanford

/**
 * @return Triple of (distance list, previous-index list for path reconstruction, has-negative-cycle)
 */
fun bellmanFord(edges: List<Triple<Int, Int, Int>>, startIndex: Int, vertexSize: Int): Triple<List<Long>, List<Int>, Boolean> {
    TODO("Implement Bellman-Ford shortest path with negative-cycle detection")
}

fun shortestPath(prevIndex: List<Int>, targetIndex: Int): List<Int> {
    TODO("Implement shortest path reconstruction from prevIndex")
}

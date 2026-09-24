package algorithm.floydwarshall

/**
 * @param matrix square adjacency matrix; use Long.MAX_VALUE / 2 for "no direct edge" to avoid overflow when relaxing.
 * @return Pair of (distance matrix, previous-index matrix for path reconstruction)
 */
fun floydWarshall(matrix: List<List<Long>>): Pair<List<List<Long>>, List<List<Int>>> {
    TODO("Implement Floyd-Warshall all-pairs shortest path")
}

fun shortestPath(prevIndex: List<List<Int>>, fromIndex: Int, toIndex: Int): List<Int> {
    TODO("Implement shortest path reconstruction from the prevIndex matrix")
}

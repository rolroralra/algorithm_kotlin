package algorithm.backtracking

/**
 * Visits [index] and its reachable neighbors depth-first, always unmarking
 * [isVisited] again before returning so the caller sees no residual state.
 *
 * @param graph Adjacency list for the graph
 * @param isVisited Mutable list of visited flags for each index in the graph
 * @param index The current index to visit
 * @param extra Optional additional arguments that can be used in the backtracking process
 *
 */
fun backtracking(graph: List<List<Int>>, isVisited: MutableList<Boolean>, index: Int, vararg extra: Any?) {
    if (isVisited[index]) {
        return
    }

    isVisited[index] = true

    for (nextIndex in graph[index]) {
        if (isVisited[nextIndex]) {
            continue
        }

        backtracking(graph, isVisited, index, extra)
    }

    isVisited[index] = false

}

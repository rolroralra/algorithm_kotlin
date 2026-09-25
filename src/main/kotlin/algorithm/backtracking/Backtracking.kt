package algorithm.backtracking

/**
 * Visits [index] and its reachable neighbors depth-first, always unmarking
 * [isVisited] again before returning so the caller sees no residual state.
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

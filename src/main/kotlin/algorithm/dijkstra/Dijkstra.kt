package algorithm.dijkstra

/**
 * @return Pair of (distance list, previous-index list for path reconstruction)
 */
fun dijkstraByPriorityQueue(graph: List<List<Pair<Int, Int>>>, startIndex: Int): Pair<List<Long>, List<Int>> {
    TODO("Implement Dijkstra using java.util.PriorityQueue")
}

fun dijkstraByHeap(graph: List<List<Pair<Int, Int>>>, startIndex: Int): Pair<List<Long>, List<Int>> {
    TODO("Implement Dijkstra using algorithm.heap.Heap")
}

fun shortestPath(prevIndex: List<Int>, targetIndex: Int): List<Int> {
    TODO("Implement shortest path reconstruction from prevIndex")
}

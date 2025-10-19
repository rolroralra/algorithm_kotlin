package algorithm.mst

import algorithm.unionfind.UnionFind
import java.util.PriorityQueue

enum class MinimalSpanningTreeAlgorithm {
    PRIM,
    KRUSKAL
}

data class Edge(val fromIndex: Int, val toIndex: Int, val length: Int)

fun minimalSpanningTree(
    graph: List<List<Pair<Int, Int>>>,
    algorithm: MinimalSpanningTreeAlgorithm = MinimalSpanningTreeAlgorithm.KRUSKAL
): Pair<Int, List<Triple<Int, Int, Int>>> {
    return when (algorithm) {
        MinimalSpanningTreeAlgorithm.PRIM -> mstPrimAlgorithm(graph)
        MinimalSpanningTreeAlgorithm.KRUSKAL -> mstKruskalAlgorithm(graph)
    }
}

fun mstKruskalAlgorithm(graph: List<List<Pair<Int, Int>>>): Pair<Int, List<Triple<Int, Int, Int>>> {
    val vertexSize = graph.size
    val unionFind = UnionFind(vertexSize)

    val sortedEdgeList = graph.withIndex()
        .flatMap { (fromIndex, list) ->
            list.map { (toIndex, edgeLength) ->
                Edge(
                    fromIndex,
                    toIndex,
                    edgeLength
                )
            }
        }
        .sortedBy { it.length }
        .distinctBy {
            // 양방향 간선을 하나로 통합
            if (it.fromIndex < it.toIndex) {
                it.fromIndex to it.toIndex
            } else {
                it.toIndex to it.fromIndex
            }
        }

    val mstEdgeList = mutableListOf<Triple<Int, Int, Int>>()
    var mstLength = 0

    for (edge in sortedEdgeList) {
        val (fromIndex, toIndex, edgeLength) = edge

        if (unionFind.isConnected(fromIndex, toIndex)) {
            continue
        }

        unionFind.union(fromIndex, toIndex)
        mstEdgeList.add(Triple(fromIndex, toIndex, edgeLength))
        mstLength += edgeLength

        if (mstEdgeList.size == vertexSize - 1) {
            break
        }
    }

    return mstLength to mstEdgeList
}

fun mstPrimAlgorithm(graph: List<List<Pair<Int, Int>>>, startIndex: Int = 0): Pair<Int, List<Triple<Int, Int, Int>>> {
    val vertexSize = graph.size
    val isVisited = MutableList(vertexSize) { false }
    val priorityQueue = PriorityQueue<Edge>(Comparator.comparingInt { it.length })

    val mstEdgeList = mutableListOf<Triple<Int, Int, Int>>()
    var mstLength = 0

    priorityQueue.add(Edge(-1, startIndex, 0))

    while (priorityQueue.isNotEmpty()) {
        val (prevIndex, currIndex, edgeLength) = priorityQueue.poll()

        if (isVisited[currIndex]) {
            continue
        }

        isVisited[currIndex] = true

        if (prevIndex in (0 until vertexSize) && currIndex in (0 until vertexSize)) {
            mstEdgeList.add(Triple(prevIndex, currIndex, edgeLength))
            mstLength += edgeLength
        }

        if (mstEdgeList.size == vertexSize - 1) {
            break
        }

        for ((nextIndex, nextEdgeLength) in graph[currIndex]) {
            if (isVisited[nextIndex]) {
                continue
            }

            priorityQueue.add(Edge(currIndex, nextIndex, nextEdgeLength))
        }
    }

    return mstLength to mstEdgeList
}


fun main() {
    val adjMatrix = listOf(
        listOf(0, 2, 3, 0, 0),  // 0: 1(2), 2(3)
        listOf(2, 0, 0, 4, 0),  // 1: 0(2), 3(4)
        listOf(3, 0, 0, 0, 1),  // 2: 0(3), 4(1)
        listOf(0, 4, 0, 0, 5),  // 3: 1(4), 4(5)
        listOf(0, 0, 1, 5, 0)   // 4: 2(1), 3(5)
    )

    val adjList = listOf(
        listOf(1 to 2, 2 to 3),  // 0
        listOf(0 to 2, 3 to 4),  // 1
        listOf(0 to 3, 4 to 1),  // 2
        listOf(1 to 4, 4 to 5),  // 3
        listOf(2 to 1, 3 to 5)   // 4
    )

    val (mstLength, mstEdgeList) = minimalSpanningTree(adjList, MinimalSpanningTreeAlgorithm.KRUSKAL)

    check(mstLength == 10)
    check(mstEdgeList.size == 4)
    check(mstEdgeList.containsAll(
        listOf(
            Triple(2,4,1),
            Triple(0,1,2),
            Triple(0,2,3),
            Triple(1,3,4)
        )))

    val (mstLength2, mstEdgeList2) = minimalSpanningTree(adjList, MinimalSpanningTreeAlgorithm.PRIM)

    check(mstLength2 == 10)
    check(mstEdgeList2.size == 4)
    check(mstEdgeList2.containsAll(
        listOf(
            Triple(2,4,1),
            Triple(0,1,2),
            Triple(0,2,3),
            Triple(1,3,4)
        )))
}
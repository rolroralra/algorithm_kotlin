package algorithm.floydwarshall

import java.util.Stack

/**
 * @param adjacentMatrix square adjacency matrix
 * @return Pair of (distance matrix, previous-index matrix for path reconstruction)
 */
fun floydWarshall(adjacentMatrix: List<List<Long>>): Pair<List<List<Long>>, List<List<Int>>> {
    val vertexCount = adjacentMatrix.size
    val distance = List(vertexCount) {
        MutableList(vertexCount) { Long.MAX_VALUE }
    }
    val prevIndex = List(vertexCount) {
        MutableList(vertexCount) { -1 }
    }

    for (fromIndex in (0 until vertexCount)) {
        distance[fromIndex][fromIndex] = 0

        for (toIndex in (0 until vertexCount)) {
            if (adjacentMatrix[fromIndex][toIndex] < Long.MAX_VALUE) {
                distance[fromIndex][toIndex] = adjacentMatrix[fromIndex][toIndex]
                prevIndex[fromIndex][toIndex] = fromIndex
            }
        }
    }

    for (k in 0 until vertexCount) {
        for (i in 0 until vertexCount) {
            if (distance[i][k] >= Long.MAX_VALUE) {
                continue
            }

            for (j in 0 until vertexCount) {
                if (distance[k][j] >= Long.MAX_VALUE) {
                    continue
                }

                val minDistanceCandidate = distance[i][k] + distance[k][j]

                if (distance[i][j] > minDistanceCandidate) {
                    distance[i][j] = minDistanceCandidate
                    prevIndex[i][j] = prevIndex[k][j]
                }
            }
        }
    }

    return distance to prevIndex
}

fun shortestPath(prevIndex: List<List<Int>>, fromIndex: Int, toIndex: Int): List<Int> {
    if (prevIndex[fromIndex][toIndex] < 0) {
        return emptyList()
    }

    val stack = Stack<Int>()
    var currIndex = toIndex

    while (currIndex >= 0) {
        stack.push(currIndex)

        if (currIndex == fromIndex) {
            break
        }

        currIndex = prevIndex[fromIndex][currIndex]
    }

    return stack.reversed()
}

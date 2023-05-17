package baekjoon.bfs

import java.util.LinkedList
import kotlin.math.max

private class Point(val x: Int, val y: Int, val z: Int) {
    fun nextPoints(xSize: Int, ySize: Int, zSize: Int): List<Point> {
        return listOf(
            listOf(1, 0, 0),
            listOf(-1, 0, 0),
            listOf(0, 1, 0),
            listOf(0, -1, 0),
            listOf(0, 0, 1),
            listOf(0, 0, -1),
        ).filter { x + it[0] in (0 until xSize) }
        .filter { y + it[1] in (0 until ySize) }
        .filter { z + it[2] in (0 until zSize) }
        .map { Point(x + it[0], y + it[1], z + it[2]) }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val inputs = br.readLine().split(" ").map { it.toInt() }
    val m = inputs[0]
    val n = inputs[1]
    val h = inputs[2]

    val arr = (1..h).map { (1..n).map { br.readLine().split(" ").map { it.toInt() }.toMutableList() } }


    val queue = LinkedList<Pair<Point, Int>>()
    arr.indices.forEach { i ->
        arr[i].indices.forEach { j ->
            arr[i][j].indices.forEach { k ->
                if (arr[i][j][k] == 1) {
                    queue.add(Point(i, j, k) to 0)
                }
            }
        }
    }

    var answer = 0
    while (queue.isNotEmpty()) {
        val (point, currDay) = queue.poll()

        answer = max(answer, currDay)

        point.nextPoints(h, n, m).forEach { nextPoint ->
            if (arr[nextPoint.x][nextPoint.y][nextPoint.z] == 0) {
                arr[nextPoint.x][nextPoint.y][nextPoint.z] = 1

                queue.add(nextPoint to currDay + 1)
            }
        }
    }


    if (arr.any { arr2 -> arr2.any { arr3 -> arr3.any { it == 0 } } }) {
        answer = -1
    }

    println(answer)
}

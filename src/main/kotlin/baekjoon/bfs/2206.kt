package baekjoon.bfs

import java.util.LinkedList

fun main() {
    val br = System.`in`.bufferedReader()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }

    val isBlocked = (1..n).map { br.readLine().map { it == '1' } }

    val isVisited = List(n) { MutableList(m) { false } }
    val isVisitedWhenOncePassedBlock = List(n) { MutableList(m) { false } }

    val queue = LinkedList<MoveInfo>()
    queue.add(MoveInfo(Position(0, 0), 1, true))
    isVisited[0][0] = true

    var answer = -1
    while (queue.isNotEmpty()) {
        val currMoveInfo = queue.poll()

        val currRow = currMoveInfo.row
        val currCol = currMoveInfo.col
        val currCount = currMoveInfo.count
        val isAbleToPassBlock = currMoveInfo.isAbleToPassBlock

        if (currRow == n - 1 && currCol == m - 1) {
            answer = currCount
            break
        }

        currMoveInfo.position.nextPositions(n, m)
            .filterNot { isVisited(it, isAbleToPassBlock, isVisited, isVisitedWhenOncePassedBlock) }
            .forEach { nextPosition ->
                if (isBlocked.get(nextPosition).not()) {
                    visit(nextPosition, isAbleToPassBlock, isVisited, isVisitedWhenOncePassedBlock)
                    queue.add(MoveInfo(nextPosition, currCount + 1, isAbleToPassBlock))
                } else if (isAbleToPassBlock) {
                    visit(nextPosition, true, isVisited, isVisitedWhenOncePassedBlock)
                    queue.add(MoveInfo(nextPosition, currCount + 1, false))
                }
            }
    }

    println(answer)
}

private fun visit(position: Position, isAbleToPassBlock: Boolean, isVisited: List<MutableList<Boolean>>, isVisitedWhenOncePassedBlock: List<MutableList<Boolean>>) {
    if (isAbleToPassBlock) {
        isVisited[position.row][position.col] = true
    } else {
        isVisitedWhenOncePassedBlock[position.row][position.col] = true
    }
}

private fun isVisited(position: Position, isAbleToPassBlock: Boolean, isVisited: List<List<Boolean>>, isVisitedWhenOncePassedBlock: List<List<Boolean>>): Boolean {
    return if (isAbleToPassBlock) {
        isVisited[position.row][position.col]
    } else {
        isVisitedWhenOncePassedBlock[position.row][position.col]
    }
}

private fun <T> List<List<T>>.get(position: Position): T = this[position.row][position.col]

private class MoveInfo(val position: Position, val count: Int, val isAbleToPassBlock: Boolean) {
    val row = position.row
    val col = position.col
}

private class Position(val row: Int, val col: Int) {
    fun nextPositions(rowSize: Int, colSize: Int): List<Position> {
        return listOf(
            listOf(1, 0),
            listOf(-1, 0),
            listOf(0, 1),
            listOf(0, -1)
        ).map { row + it[0] to col + it[1] }
            .filter { it.first in 0 until rowSize }
            .filter { it.second in 0 until colSize }
            .map { Position(it.first, it.second) }
    }

    override fun toString(): String {
        return "($row, $col)"
    }
}

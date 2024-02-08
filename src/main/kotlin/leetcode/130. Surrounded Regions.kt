package leetcode

import java.util.LinkedList
import java.util.Queue

fun main() {
    val arr = arrayOf(
        charArrayOf('X', 'X', 'X', 'X'),
        charArrayOf('X', 'O', 'O', 'X'),
        charArrayOf('X', 'X', 'O', 'X'),
        charArrayOf('X', 'O', 'X', 'X')
    )
    solve(arr)

    arr.forEach {
        println(it.contentToString())
    }
}

private fun solve(board: Array<CharArray>) {
    val isVisited = Array(board.size) { BooleanArray(board.first().size) }

    board.indices.forEach { row ->
        board[row].withIndex().forEach { (col, value) ->
            if ('O' == value && !isVisited[row][col]) {
                bfs(row to col, board, isVisited)
            }
        }
    }

}

private fun bfs(pair: Pair<Int, Int>, board: Array<CharArray>, isVisited: Array<BooleanArray>) {
    var isSurrounded = true
    val modifiedPairList = mutableListOf<Pair<Int, Int>>()

    val queue: Queue<Pair<Int, Int>> = LinkedList()

    queue.add(pair)
    modifiedPairList.add(pair)
    isVisited[pair.first][pair.second] = true

    while (queue.isNotEmpty()) {
        val currentPosition = queue.poll()

        for (nextPair in currentPosition.nextPairs()) {
            if (!nextPair.isValidIn(board.size, board.first().size)) {
                isSurrounded = false
                continue
            }

            if (!isVisited[nextPair.first][nextPair.second] && board[nextPair.first][nextPair.second] == 'O') {
                isVisited[nextPair.first][nextPair.second] = true
                queue.add(nextPair)

                modifiedPairList.add(nextPair)
            }
        }
    }

    if (isSurrounded) {
        modifySurroundedRegion(board, modifiedPairList)
    }
}

private fun Pair<Int, Int>.nextPairs(): Sequence<Pair<Int, Int>> {
    return sequenceOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        .map(this::plus)
}

private fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return first + other.first to second + other.second
}

private fun Pair<Int, Int>.isValidIn(rowSize: Int, colSize: Int): Boolean {
    return first in (0 until rowSize) && second in (0 until colSize)
}

private fun modifySurroundedRegion(board: Array<CharArray>, modifiedPairList: List<Pair<Int, Int>>) {
    modifiedPairList.forEach {
        board[it.first][it.second] = 'X'
    }
}

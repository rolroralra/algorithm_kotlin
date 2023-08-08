package leetcode

private fun isValidSudoku(board: Array<CharArray>): Boolean {
    return board.indices.all { row ->
        board[row]
            .filterNot { it == '.' }
            .groupBy { it }
            .all { it.value.size == 1 }
    }.and(
        board[0].indices.all { col ->
            board
                .map { it[col] }
                .filterNot { it == '.' }
                .groupBy { it }
                .all { it.value.size == 1 }
        }
    ).and(
        (0 until board.size / 3).all { row ->
            (0 until board[0].size / 3).all { col ->
                board
                    .slice(row * 3 until (row + 1) * 3)
                    .flatMap { it.slice(col * 3 until (col + 1) * 3) }
                    .filterNot { it == '.' }
                    .groupBy { it }
                    .all { it.value.size == 1 }
            }
        }
    )
}


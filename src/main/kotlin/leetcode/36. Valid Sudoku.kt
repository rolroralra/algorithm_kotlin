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

private fun isValidSudoku2(board: Array<CharArray>): Boolean {
    val seen = HashSet<String>()

    for(i in 0 until 9){
        for(j in 0 until 9){
            var number = board[i][j]
            if(number != '.'){
                if(!seen.add("$number in row $i") ||
                    !seen.add("$number in column $j") ||
                    !seen.add("$number in block ${i/3}-${j/3}")) return false

            }
        }
    }

    return true
}


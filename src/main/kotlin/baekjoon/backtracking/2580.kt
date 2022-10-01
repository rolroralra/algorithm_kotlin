package baekjoon.backtracking

fun main() {
    val graph = (0 until 9)
        .map {
            readln().split(" ")
                .map { it.toInt() }
                .toMutableList()
        }.toList()
        .toMutableList()

    val visitList = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until 9) {
        for (j in  0 until 9) {
            if (graph[i][j] == 0) {
                visitList.add(i to j)
            }
        }
    }

    backtracking( 0, visitList, graph)
}

fun backtracking(visitDepth: Int, visitList: List<Pair<Int, Int>>, graph: MutableList<MutableList<Int>>): Boolean {
    if (visitDepth == visitList.size) {
        for (i in 0 until 9) {
            for (j in  0 until 9) {
                print("${graph[i][j]} ")
            }
            println()
        }
        return true
    }

    val (row, col) = visitList[visitDepth]

    for (value  in 1..9) {
        if (isValid(row, col, value, graph)) {
            graph[row][col] = value
            if (backtracking(visitDepth + 1, visitList, graph)) {
                return true
            }

            graph[row][col] = 0
        }
    }

    return false
}

fun isValid(row: Int, col: Int, value: Int, graph: List<List<Int>>): Boolean =
    isValidInBlock(row, col, value, graph)
            && isValidInCurrentColumn(row, col, value, graph)
            && isValidInCurrentRow(row, col, value, graph)

fun isValidInBlock(row: Int, col: Int, value: Int, graph: List<List<Int>>): Boolean {
    val blockRowIndex = (row / 3) * 3
    val blockColIndex = (col / 3) * 3

    for (rowIndex in blockRowIndex until blockRowIndex + 3) {
        for (colIndex in blockColIndex until blockColIndex + 3) {
            if (graph[rowIndex][colIndex] == value) {
                return false
            }
        }
    }

    return true
}

fun isValidInCurrentColumn(row: Int, col: Int, value: Int, graph:  List<List<Int>>): Boolean {
    for (rowIndex in 0 until 9) {
        if (graph[rowIndex][col] == value) {
            return false
        }
    }

    return true
}

fun isValidInCurrentRow(row: Int, col: Int, value: Int, graph:  List<List<Int>>): Boolean {
    for (colIndex in 0 until 9) {
       if (graph[row][colIndex] == value) {
           return false
       }
    }

    return true
}


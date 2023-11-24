package leetcode

fun main() {
    NumMatrix(
        arrayOf(
            intArrayOf(3, 0, 1, 4, 2),
            intArrayOf(5, 6, 3, 2, 1),
            intArrayOf(1, 2, 0, 1, 5),
            intArrayOf(4, 1, 0, 1, 7),
            intArrayOf(1, 0, 3, 0, 5)
        )
    ).apply {
        println(sumRegion(2, 1, 4, 3)) // 8
        println(sumRegion(1, 1, 2, 2)) // 11
        println(sumRegion(1, 2, 2, 4)) // 12
    }
}

class NumMatrix(matrix: Array<IntArray>) {

    private val sum: Array<IntArray> = Array(matrix.size) { IntArray(matrix[0].size) { 0 } }.apply {
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, matrixValue ->
                this[rowIndex][colIndex] = this.getOrZero(rowIndex - 1, colIndex) +
                        this.getOrZero(rowIndex, colIndex - 1) -
                        this.getOrZero(rowIndex - 1, colIndex - 1) + matrixValue
            }
        }
    }


    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return sum.getOrZero(row2, col2) -
                sum.getOrZero(row1 - 1, col2) -
                sum.getOrZero(row2, col1 - 1) +
                sum.getOrZero(row1 - 1, col1 - 1)
    }
}

private fun Array<IntArray>.getOrZero(rowIndex: Int, colIndex: Int): Int {
    return this.getOrNull(rowIndex)?.getOrNull(colIndex) ?: 0
}

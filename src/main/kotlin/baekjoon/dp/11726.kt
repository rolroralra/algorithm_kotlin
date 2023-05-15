package baekjoon.dp

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    println(fibonacci(n, 1, 1))
}

private fun fibonacci(n: Int, a0: Int = 0, a1: Int = 1): Int {
    if (n == 0) return a0
    else if (n == 1) return a1


    val booleans = (n - 1).toString(2).reversed().map { it == '1' }

    val matrixList = MutableList(booleans.size) { MatrixD2.identity(2) }

    matrixList[0] = MatrixD2(listOf(1, 1), listOf(1, 0))
    (1..matrixList.lastIndex).forEach {
        matrixList[it] = matrixList[it - 1] * matrixList[it - 1]
    }

    val result = booleans.withIndex().fold(MatrixD2.identity(2)) { acc, v ->
        if (v.value) {
            acc * matrixList[v.index]
        } else
            acc
    } * MatrixD2(listOf(a1), listOf(a0))

    return result[0][0]
}

private class MatrixD2 private constructor(private val matrix: List<MutableList<Int>>) {
    constructor(vararg lists: List<Int>) : this(lists.map { it.toMutableList() })

    constructor(rowSize: Int, colSize: Int) : this(List(rowSize) { MutableList(colSize) { 0 } })

    operator fun times(other: MatrixD2): MatrixD2 {
        val result = MatrixD2(matrix.size, other[0].size)

        matrix.indices.forEach { row ->
            other[0].indices.forEach { col ->
                val sum = this[0].indices.fold(0) { acc, k ->
                    (acc + (this[row][k] * other[k][col]) % 10_007) % 10_007
                }

                result[row][col] = sum
            }
        }

        return result
    }

    operator fun get(row: Int): MutableList<Int> {
        return matrix[row]
    }

    operator fun get(row: Int, col: Int): Int {
        return this[row][col]
    }

    companion object {
        fun identity(size: Int): MatrixD2 {
            val result = MatrixD2(size, size)

            (0 until size).forEach {
                result[it][it] = 1
            }

            return result
        }
    }
}



package baekjoon.divideNconquer

fun main() {
    val br = System.`in`.bufferedReader()

    val (n, b) = br.readLine().split(" ").map { it.toLong() }.let { it[0].toInt() to it[1] }

    val a = (1..n).map { br.readLine().split(" ").map { it.toInt() } }

    println(a.pow(b).toMatrixString())
}

private fun List<List<Int>>.multiply(other: List<List<Int>>): List<List<Int>> {
    val result = List(size) { MutableList(other[0].size) { 0 } }

    (indices).forEach { i ->
        (other[0].indices).forEach { j ->
            (other.indices).forEach { k ->
                result[i][j] = (result[i][j] + this[i][k] * other[k][j]) % 1000
            }
        }
    }

    return result
}

private fun List<List<Int>>.pow(n: Long): List<List<Int>> {
    if (n == 1L) {
        return this
    }

    val half = pow(n / 2)

    return if (n % 2 == 0L) {
        half.multiply(half)
    } else {
        half.multiply(half).multiply(this)
    }
}

private fun List<List<Int>>.toMatrixString(): String {
    return joinToString("\n") {
        it.map { v -> v % 1000 }.joinToString(" ")
    }
}

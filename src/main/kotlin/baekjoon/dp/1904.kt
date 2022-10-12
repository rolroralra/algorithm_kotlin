package baekjoon.dp

fun main() {
    val br = System.`in`.bufferedReader()
    var n = br.readLine().trim().toInt()

    var size = 1
    var count = 1
    while (size < n) {
        size *= 2
        count++
    }

    val cache = Array<Matrix?>(count + 1) { null }
    cache[0] = Matrix(2)
    cache[0]!!.array[0][0] = 1
    cache[0]!!.array[0][1] = 1
    cache[0]!!.array[1][0] = 1
    cache[0]!!.array[1][1] = 0

    for (i in 1..count) {
        cache[i] = cache[i - 1]!!.multiple(cache[i - 1]!!)
    }

    println(
        when (n) {
            in 1..2 -> n
            else -> {
                n -= 2
                var index = 0
                var result = Matrix.identity(2)
                while (n > 0) {
                    if (n % 2 == 1) {
                        result = result.multiple(cache[index]!!)
                    }

                    n /= 2
                    index++
                }

                (result.array[0][0] * 2 + result.array[0][1]) % 15746
            }
        }
    )
}


class Matrix(n: Int) {
    val array: Array<Array<Int>>

    init {
        array = Array(n) { Array(n) { 0 } }
    }

    companion object {
        fun identity(n: Int): Matrix {
            val result = Matrix(n)
            for (i in result.array.indices) {
                result.array[i][i] = 1
            }

            return result
        }
    }

    fun multiple(other: Matrix): Matrix {
        val result = Matrix(array.size)
        for (i in array.indices) {
            for (j in array[i].indices) {
                for (k in array[i].indices) {
                    result.array[i][j] = (result.array[i][j] + (array[i][k] * other.array[k][j]) % 15746) % 15746
                }
            }
        }

        return result
    }
}
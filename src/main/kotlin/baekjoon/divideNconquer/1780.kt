package baekjoon.divideNconquer

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val list = (1..n).map { br.readLine().split(" ").map { it.toInt() } }

    divideAndConquer(0, 0, n, list).forEach { println(it) }
}

private fun divideAndConquer(row: Int, col: Int, n: Int, list: List<List<Int>>): List<Int> {
    val result = mutableListOf(0, 0, 0)

    if (n <= 1) {
        result[list[row][col] + 1] = 1
        return result
    }

    val nextN = n / 3

    val addResult = mutableListOf(0, 0, 0)
    val setResult = mutableSetOf<Int>()
    (0 until 3).forEach { i ->
        (0 until 3).forEach { j ->
            val divideAndConquerResult = divideAndConquer(row + nextN * i, col + nextN * j, nextN, list)

            setResult.addAll(divideAndConquerResult.indices.filter { divideAndConquerResult[it] > 0 })

            for (k in divideAndConquerResult.indices) {
                addResult[k] += divideAndConquerResult[k]
            }
        }
    }

    if (setResult.size == 1) {
        result[setResult.first()]++
    } else {
        for (i in addResult.indices) {
            result[i] += addResult[i]
        }
    }

    return result.toList()
}

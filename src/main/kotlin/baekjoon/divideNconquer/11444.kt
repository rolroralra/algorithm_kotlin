package baekjoon.divideNconquer

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toLong()

    println(fibonacci(n))
}


private fun fibonacci(n: Long): Int {
    if (n == 0L) return 0
    else if (n == 1L) return 1

    val log2n = log2(n)

    val cache = MutableList<List<List<Long>>>(log2n + 1) { List(2) { MutableList(2) { 0 } } }

    cache[0] = listOf(mutableListOf(1, 1), mutableListOf(1, 0))
    (1..log2n).map {
        cache[it] = cache[it - 1].multiply(cache[it - 1])
    }


    val result = (n - 1).toString(2)
        .reversed()
        .map { it == '1' }
        .withIndex()
        .filter { it.value }
        .map { cache[it.index] }
        .fold(listOf(listOf(1, 0), listOf(0, 1))) { acc: List<List<Long>>, matrix: List<List<Long>> ->
            acc.multiply(matrix)
        }

    return result.multiply(listOf(listOf(1), listOf(0)))[0][0].toInt()
}

private fun log2(n: Long): Int {
    if (n <= 1) {
        return 0
    }

    return generateSequence(0 to 1L) { it.first + 1 to (it.second * 2) }.filter { it.second >= n }
        .first().first

}

private fun List<List<Long>>.multiply(other: List<List<Long>>): List<List<Long>> {
    val result = List(this.size) { MutableList(other.first().size) { 0L } }

    result.indices.forEach { i ->
        result.first().indices.forEach { j ->
            other.indices.forEach { k ->
                result[i][j] += (this[i][k] * other[k][j]) % 1_000_000_007L
                result[i][j] %= 1_000_000_007L
            }
        }
    }

    return result
}

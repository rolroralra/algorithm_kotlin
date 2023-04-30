package baekjoon.binarysearch

fun main() {
    val br = System.`in`.bufferedReader()

    val inputs = br.readLine().split(" ").map { it.toInt() }

    val a = inputs[0]
    val b = inputs[1]
    val c = inputs[2]

    val reversedBinaryString = b.toString(2).reversed().map { it == '1' }

    val cache = MutableList(reversedBinaryString.size) { 0L }

    cache[0] = a.toLong() % c

    (1 until cache.size).forEach { i ->
        cache[i] = (cache[i - 1] * cache[i - 1]) % c
    }

    val result = reversedBinaryString.withIndex()
        .filter { it.value }
        .map { cache[it.index] }
        .fold(1L) { acc, v -> (acc * v) % c }

    println(result)
}



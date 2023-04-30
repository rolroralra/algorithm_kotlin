package baekjoon.binarysearch

fun main() {
    val br = System.`in`.bufferedReader()

    val inputs = br.readLine().split(" ").map { it.toInt() }

    val n = inputs[0]
    val k = inputs[1]

    println(combination(n, k))
}

private fun combination(n: Int, k: Int): Int {
    val mod = 1_000_000_007

    if (k == 0 || k == n) {
        return 1
    }

    val divisor = (factorial(k, mod).toLong() * factorial(n - k, mod)) % mod

    return ((factorial(n, mod).toLong() * pow(divisor.toInt(), mod - 2, mod)) % mod).toInt()
}

private fun factorial(n: Int, mod: Int): Int {
    return (1..n).fold(1L) { acc, v -> (acc * v) % mod }.toInt()
}

private fun pow(a: Int, b: Int, mod: Int): Int {
    val reversedBinaryString = b.toString(2).reversed().map { it == '1' }

    val cache = MutableList(reversedBinaryString.size) { 0L }

    cache[0] = a.toLong() % mod

    (1 until cache.size).forEach { i ->
        cache[i] = (cache[i - 1] * cache[i - 1]) % mod
    }

    return reversedBinaryString.withIndex()
        .filter { it.value }
        .map { cache[it.index] }
        .fold(1L) { acc, v -> (acc * v) % mod }.toInt()
}

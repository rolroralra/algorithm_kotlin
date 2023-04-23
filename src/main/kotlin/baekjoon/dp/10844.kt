package baekjoon.dp

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val mod = 1_000_000_000

    val cache = MutableList(n + 1) { MutableList(10) { 0 } }

    (1..9).forEach { cache[1][it] = 1 }

    (2..n).forEach { i ->
        (0..9).forEach { j ->
            cache[i][j] = when (j) {
                0 -> cache[i - 1][1]
                in (1..8) -> (cache[i - 1][j - 1] + cache[i - 1][j + 1]) % mod
                9 -> cache[i - 1][8]
                else -> 0
            }
        }
    }

    println(cache[n].fold(0) { acc, v -> (acc + v) % mod })
}

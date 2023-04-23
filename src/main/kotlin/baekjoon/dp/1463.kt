package baekjoon.dp

import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val cache = MutableList(if (n <= 3) 4 else n + 1) { -1 }
    cache[1] = 0
    cache[2] = 1
    cache[3] = 1

    (4..n).forEach {
        cache[it] = cache[it - 1] + 1

        if (it % 3 == 0) {
            cache[it] = min(cache[it], cache[it / 3] + 1)
        }

        if (it % 2 == 0) {
            cache[it] = min(cache[it], cache[it / 2] + 1)
        }
    }

    println(cache[n])
}


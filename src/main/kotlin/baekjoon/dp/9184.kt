package baekjoon.dp

fun main() {
    val br = System.`in`.bufferedReader()

    while (true) {
        val (a, b, c) = br.readLine().trim().split(" ").map { it.toInt() }

        if (a == -1 && b == -1 && c == -1) {
            break
        }

        println("w(${a}, ${b}, ${c}) = ${w(a, b, c)}")
    }
}

val cache = Array(101) { Array(101) { IntArray(101) } }

fun w(a: Int, b: Int, c: Int): Int {
    val (aa, bb, cc) = listOf(a + 50, b + 50, c + 50)
    if (cache[aa][bb][cc] != 0) {
        return cache[aa][bb][cc]
    }

    if (a <= 0 || b <= 0 || c <= 0) {
        cache[aa][bb][cc] = 1
        return cache[aa][bb][cc]
    }

    if (a > 20 || b > 20 || c > 20) {
        cache[aa][bb][cc] = w(20, 20, 20)
        return cache[aa][bb][cc]
    }

    if (b in (a + 1) until c) {
        cache[aa][bb][cc] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c)
    } else {
        cache[aa][bb][cc] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1)
    }

    return cache[aa][bb][cc]
}
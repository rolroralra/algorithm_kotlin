package baekjoon.divideNconquer

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().split(" ").map { it.toInt() }.let { it[0] }

    val a = (1..n).map {
        br.readLine().split(" ").map { it.toInt() }
    }

    val (m, k) = br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }

    val b = (1..m).map {
        br.readLine().split(" ").map { it.toInt() }
    }


    val result = List(n) { MutableList(k) { 0 } }
    (0 until n).forEach { i ->
        (0 until k).forEach { j ->
            result[i][j] = (0 until m).sumOf { a[i][it] * b[it][j] }
        }
    }

    result.forEach {
        println(it.joinToString(" "))
    }
}

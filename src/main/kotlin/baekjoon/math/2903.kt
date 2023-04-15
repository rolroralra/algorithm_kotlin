package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val `2powN` = (1..n).fold(1L) { acc, _ -> acc * 2L }

    val result = 1 + `2powN`

    println(result * result)
}

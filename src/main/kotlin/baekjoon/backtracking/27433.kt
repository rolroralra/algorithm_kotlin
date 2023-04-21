package baekjoon.backtracking

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toLong()

    println((1..n).fold(1L) { acc, i -> acc * i })
}

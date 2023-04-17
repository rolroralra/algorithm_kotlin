package baekjoon.combination

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    println((1..n).fold(1) { acc, _ -> acc * 2 })
}

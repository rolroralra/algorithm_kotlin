package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    println((1..n).takeWhile { it * it <= n }.count())
}

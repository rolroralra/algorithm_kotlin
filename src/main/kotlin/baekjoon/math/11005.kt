package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()
    val inputs = br.readLine().split(" ").map { it.toInt() }
    val (n, b) = inputs[0] to inputs[1]

    println(n.toString(b).uppercase())
}

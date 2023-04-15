package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    val inputs = br.readLine().split(" ").map { it.toInt() }

    val (n, k) = inputs[0] to inputs[1] - 1

    val divisorList = (1..n).filter { n % it == 0 }.toList()

    println(if (k in divisorList.indices) divisorList[k] else 0)
}

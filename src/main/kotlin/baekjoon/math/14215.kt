package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    val inputs = br.readLine().split(" ").map { it.toInt() }.sorted()

    val a = inputs[0]
    val b = inputs[1]
    val c = inputs[2]

    println(optimizeSum(a, b, c))
}

private fun optimizeSum(a: Int, b: Int, c: Int): Int {
    return a + b + (if (c >= a + b) a + b - 1  else c)
}

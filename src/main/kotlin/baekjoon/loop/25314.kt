package baekjoon.loop

fun main() {
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt() / 4

    println((1..N).joinToString(" ", "", " int") { "long" })
}

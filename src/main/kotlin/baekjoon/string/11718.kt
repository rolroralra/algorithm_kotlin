package baekjoon.string

fun main() {
    val br = System.`in`.bufferedReader()
    println(br.lineSequence().takeWhile { it.isNotEmpty() }.joinToString("\n"))
}

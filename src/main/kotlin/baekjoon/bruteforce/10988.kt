package baekjoon.bruteforce

fun main() {
    val br = System.`in`.bufferedReader()

    val input = br.readLine().trim()

    println(if (input == input.reversed()) 1 else 0)
}

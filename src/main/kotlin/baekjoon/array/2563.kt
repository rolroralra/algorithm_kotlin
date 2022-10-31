package baekjoon.array

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    fun input() = br.readLine().trim()

    val array = Array(101) { Array(101) { 0 } }

    val n = input().toInt()
    repeat(n) {
        val (x, y) = input().split(" ").map { it.toInt() }

        for (row in x until x + 10) {
            for (col in y until y + 10) {
                array[row][col] = 1
            }
        }
    }

    println(array.fold(0) { acc, arr -> acc + arr.fold(0, Int::plus) })
}
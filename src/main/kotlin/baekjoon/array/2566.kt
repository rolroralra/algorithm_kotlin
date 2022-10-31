package baekjoon.array

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    fun input() = br.readLine().trim()

    var maxValue = -1
    var maxRow = 0
    var maxCol = 0
    for (i in 1..9) {
        for ((j, value) in input().split(" ").map { it.toInt() }.withIndex()) {
            if (value > maxValue) {
                maxValue = value
                maxRow = i
                maxCol = j + 1
            }
        }
    }

    println(maxValue)
    println("$maxRow $maxCol")
}
package baekjoon.array

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    fun input() = br.readLine().trim()

    val studentSet = (1..30).toSortedSet().toMutableSet()
    repeat(28) {
        studentSet.remove(input().toInt())
    }

    studentSet.forEach{ println(it) }
}
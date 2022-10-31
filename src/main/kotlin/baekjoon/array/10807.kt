package baekjoon.array

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    fun input() = br.readLine().trim()

    input().toInt()
    val list = input().split(" ").map { it.toInt() }

    val target = input().toInt()

    println(list.count { it == target })
}
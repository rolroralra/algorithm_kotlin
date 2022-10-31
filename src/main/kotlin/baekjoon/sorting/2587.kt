package baekjoon.sorting

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    fun input() = br.readLine().trim()

    val list = mutableListOf<Int>()
    repeat(5) {
        list.add(input().toInt())
    }

    println(list.average().toInt())
    println(list.sorted()[2])
}


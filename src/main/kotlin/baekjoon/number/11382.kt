package baekjoon.number

import java.math.BigInteger

fun main() {
    val br = System.`in`.bufferedReader()

    println(br.readLine().split(" ")
        .map { BigInteger(it) }
        .reduce { acc, bigInteger -> acc.plus(bigInteger) }
    )
}


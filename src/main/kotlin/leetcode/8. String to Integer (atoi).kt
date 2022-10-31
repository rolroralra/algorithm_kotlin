package leetcode

import java.math.BigInteger

fun main() {
    println(myAtoi("42"))
    println(myAtoi("   -42"))
    println(myAtoi("4193 with words"))
    println(myAtoi("123-"))
    println(myAtoi("-91283472332"))

}

fun myAtoi(s: String): Int {
    var isNegative: Boolean? = null
    var isTerminated = false
    val input = s.trim()
    val output = buildString {
        for (c in input) {
            if (isTerminated) {
                break
            }

            when (c) {
                '+', '-' -> if (isNegative != null || isNotEmpty()) isTerminated = true else {
                    isNegative = (c == '-')
                    append(c)
                }
                in '0'..'9' -> {
                    append(c)
                }
                else -> isTerminated = true
            }
        }
    }

    return try {
        val bigInt = output.toBigInteger()
        if (bigInt < BigInteger(Integer.MIN_VALUE.toString(), 10)) {
            return Integer.MIN_VALUE
        }
        else if (bigInt > BigInteger(Integer.MAX_VALUE.toString(), 10)) {
            return Integer.MAX_VALUE
        }

        return bigInt.toInt()
    } catch (e: Exception) {
        0
    }
}
package leetcode

import java.math.BigInteger

fun main() {
    println(plusOne(intArrayOf(1,2,3)).contentToString())
    println(plusOne(intArrayOf(4,3,2,1)).contentToString())
    println(plusOne(intArrayOf(9)).contentToString())
    println(plusOne(intArrayOf(9,8,7,6,5,4,3,2,1,0)).contentToString())
}

fun plusOne(digits: IntArray): IntArray {
    val result = digits.asSequence()
        .joinToString("")
        .toBigInteger().plus(BigInteger.ONE)

    return result.toString().asSequence()
        .map { it - '0' }
        .toList()
        .toIntArray()
}

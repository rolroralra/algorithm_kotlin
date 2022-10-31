package leetcode

// 29. Divide Two Integers
fun main() {
    println(divide(-2147483648, -1))
}

fun divide(dividend: Int, divisor: Int): Int {
    val longResult = dividend.toLong().div(divisor.toLong())

    return if (longResult > Int.MAX_VALUE) {
        Int.MAX_VALUE
    } else if (longResult < Int.MIN_VALUE) {
        Int.MIN_VALUE
    } else {
        longResult.toInt()
    }
}
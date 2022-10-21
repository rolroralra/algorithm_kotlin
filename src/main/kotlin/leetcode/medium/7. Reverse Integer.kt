package leetcode.medium

import kotlin.math.abs

fun main() {
    println(reverse(123))
    println(reverse(-123))
    println(reverse(120))

    println(reverse(Integer.MAX_VALUE))
}

fun reverse(x: Int) : Int {
    val reversed = abs(x.toLong()).toString().reversed().trimStart('0')
    var absReversed = if (reversed.isBlank()) 0 else reversed.toLong()
    if (x < 0) {
        absReversed *= -1
    }

    if (absReversed < Integer.MIN_VALUE || absReversed > Integer.MAX_VALUE) {
        return 0
    }

    return absReversed.toInt()
}
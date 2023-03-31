package leetcode

import kotlin.RuntimeException

fun main() {
    println(mySqrt(4))
    println(mySqrt(8))
    println(mySqrt(0))
}

fun mySqrt(x: Int): Int {
    for (i in 0..Int.MAX_VALUE / 2) {
        val multipleResult = i.toLong() * i
        if (multipleResult > x.toLong()) {
            return i - 1
        }
    }

    throw RuntimeException()
}

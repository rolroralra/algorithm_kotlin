package baekjoon.geometry

import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    val r = Integer.parseInt(readln())
    println(BigDecimal(Math.PI * r * r).setScale(6, RoundingMode.HALF_UP))
    println(BigDecimal(r * r * 2).setScale(6, RoundingMode.HALF_UP))

}

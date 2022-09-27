package baekjoon.geometry

import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.sqrt

fun main() {
    val t = Integer.parseInt(readln())

    for (testCase in 1..t) {
        val st = StringTokenizer(readln())
        val x1 = Integer.parseInt(st.nextToken()).toDouble()
        val y1 = Integer.parseInt(st.nextToken()).toDouble()
        val r1 = Integer.parseInt(st.nextToken()).toDouble()
        val x2 = Integer.parseInt(st.nextToken()).toDouble()
        val y2 = Integer.parseInt(st.nextToken()).toDouble()
        val r2 = Integer.parseInt(st.nextToken()).toDouble()

        val d = sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)))

        val d1 = r1 + r2
        val d2 = abs(r1 - r2)

        val result: Int = if (d > d1) {
            0
        } else if (d == d1) {
            1
        } else if (d < d1 && d > d2) {
            2
        } else if (d > 0 && d == d2) {
            1
        } else if (d > 0 && d < d2) {
            0
        } else if (r1 == r2) {
            -1
        } else {
            0
        }

        println(result)
    }



}
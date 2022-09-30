package baekjoon.number

import java.util.StringTokenizer

fun main() {
    while (true) {
        val st = StringTokenizer(readln())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        if (a == 0 && b == 0) {
            break
        }

        println(when {
            a.mod(b) == 0 -> "multiple"
            b.mod(a) == 0 -> "factor"
            else -> "neither"
        })
    }
}
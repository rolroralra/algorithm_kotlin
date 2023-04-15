package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    while (true) {
        val n = br.readLine().toInt()

        if (n == -1) {
            break
        }

        println(if (isCompletedNumber(n)) "$n = ${getDivisorList(n).joinToString(" + ")}" else "$n is NOT perfect.")
    }
}

fun isCompletedNumber(n: Int): Boolean {
    return getDivisorList(n).sum() == n
}

fun getDivisorList(n: Int): List<Int> {
    return (1 until n).filter { n % it == 0 }.toList()
}

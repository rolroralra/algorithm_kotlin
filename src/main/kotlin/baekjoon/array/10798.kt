package baekjoon.array

fun main() {
    val n = 5

    val br = System.`in`.bufferedReader()
    val arr = (1..n).map { br.readLine().trim() }

    val maxLength = arr.asSequence().map { it.length }.maxOf { it }

    println(
        (0 until maxLength).joinToString("") { i ->
            (0 until n)
                .filter { i in arr[it].indices }
                .map { arr[it][i] }
                .joinToString("")
        }
    )
}

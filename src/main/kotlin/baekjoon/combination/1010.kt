package baekjoon.combination

fun main() {
    val br = System.`in`.bufferedReader()

    val testCount = br.readLine().toInt()

    (1..testCount).forEach { _ ->
        val inputs = br.readLine().split(" ").map { it.toInt() }

        val result = (0 until inputs[0]).fold(1) { acc, i -> acc * (inputs[1] - i) / (i + 1)}

        println(result)
    }
}

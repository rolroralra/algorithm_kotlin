package baekjoon.array

fun main() {
    val br = System.`in`.bufferedReader()

    val inputs = br.readLine().split(" ").map { it.toInt() }
    val N = inputs[0]
    val M = inputs[1]

    val arr = IntArray(N) { 0 }

    (1..M).forEach { _ ->
        val commands = br.readLine().split(" ").map { it.toInt() }

        val i = commands[0] - 1
        val j = commands[1] - 1
        val k = commands[2]

        (i..j).forEach {
            arr[it] = k
        }
    }

    println(arr.asSequence().joinToString(" "))
}

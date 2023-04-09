package baekjoon.array

fun main() {
    val br = System.`in`.bufferedReader()
    val inputs = br.readLine().split(" ").map { it.toInt() }

    val N =  inputs[0]
    val M =  inputs[1]

    val arr = IntArray(N) { it + 1 }

    (1..M).forEach { _ ->
        val commands = br.readLine().split(" ").map { it.toInt() - 1 }

        val i = commands[0]
        val j = commands[1]


        arr[i] = arr[j].also { arr[j] = arr[i] }
    }

    println(arr.joinToString(" "))
}

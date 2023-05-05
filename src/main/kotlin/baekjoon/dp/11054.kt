package baekjoon.dp

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val arr = br.readLine().split(" ").map { it.toInt() }

    val lis = MutableList<Int>(n) { 1 }
    val lds = MutableList<Int>(n) { 1 }

    for (i in 0 until n) {
        for (j in 0 until i) {
            if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                lis[i] = lis[j] + 1
            }
        }
    }

    for (i in n - 1 downTo 0) {
        for (j in i + 1 until n) {
            if (arr[i] > arr[j] && lds[i] < lds[j] + 1) {
                lds[i] = lds[j] + 1
            }
        }
    }

    val bitonicSeriesLength = lis.indices.map { lis[it] + lds[it] - 1 }.maxOf { it }

    println(bitonicSeriesLength)
}

package baekjoon.greedy

fun main() {

    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val distances = br.readLine().split(" ").map { it.toInt() }
    val prices = br.readLine().split(" ").map { it.toInt() }

    var currPrice = Int.MAX_VALUE
    var result = 0L
    distances.indices.forEach { i ->
        if (currPrice > prices[i]) {
            currPrice = prices[i]
        }

        result += currPrice.toLong() * distances[i]
    }

    println(result)
}

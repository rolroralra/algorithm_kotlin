package baekjoon.binarysearch

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val numbers = br.readLine().split(" ").map { it.toInt() }.sorted()
    val m = br.readLine().toInt()
    val queries = br.readLine().split(" ").map { it.toInt() }

    println(
        queries.map { q ->
        if (numbers.binarySearch(q) in numbers.indices) 1 else 0
        }.joinToString("\n")
    )
}

package baekjoon.segment.tree

fun main() {
    val br = System.`in`.bufferedReader()

    val input = br.readLine()

    val map = ('a'..'z').associateWith {  MutableList(input.length) { 0 } }.toMutableMap()

    val q = br.readLine().toInt()

    input.indices.forEach { i ->
        if (i > 0) {
            map.values.forEach { it[i] = it[i - 1] }
        }

        map[input[i]]?.set(i, (map[input[i]]?.get(i) ?: 0) + 1)
    }

    (1..q).forEach { _ ->
        val queries = br.readLine().split(" ")

        val character = queries[0].first()

        val start = queries[1].toInt()
        val end = queries[2].toInt()

        val startCount = if (start == 0) 0 else map[character]?.get(start - 1) ?: 0
        val endCount = map[character]?.get(end) ?: 0

        println(endCount - startCount)
    }
}

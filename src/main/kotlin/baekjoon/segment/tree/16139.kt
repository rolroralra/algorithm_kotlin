package baekjoon.segment.tree

fun main() {
    val br = System.`in`.bufferedReader()

    val input = br.readLine()

    val arr = Array(('a'..'z').count()) { IntArray(input.length) { 0 } }

    val q = br.readLine().toInt()

    arr.indices.forEach { i ->
        val code = i + 'a'.code
        repeat(input.length) { j ->
            if (j > 0) {
                arr[i][j] = arr[i][j - 1]
            }

            if (input[j].code == code) {
                arr[i][j]++
            }
        }
    }

    println(
        (1..q).map { _ ->
            val queries = br.readLine().split(" ")

            val character = queries[0].first()
            val index = indexOf(character)

            val start = queries[1].toInt()
            val end = queries[2].toInt()

            val startCount = if (start == 0) 0 else arr[index][start - 1]
            val endCount = arr[index][end]

            endCount - startCount
        }.joinToString("\n")
    )
}

private fun indexOf(c: Char): Int {
    return c.code - 'a'.code
}

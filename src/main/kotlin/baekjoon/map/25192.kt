package baekjoon.map

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val set = mutableSetOf<String>()
    var result = 0
    (1..n).forEach { _ ->
        val input = br.readLine()

        if (input == "ENTER") {
            set.clear()
        } else if (!set.contains(input)) {
            set.add(input)
            result++
        }
    }

    println(result)
}

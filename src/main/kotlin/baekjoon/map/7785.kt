package baekjoon.map

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val set = mutableSetOf<String>()

    (1..n).forEach { _ ->
        val inputs = br.readLine().split(" ")
        when(inputs[1]) {
            "enter" -> set.add(inputs[0])
            "leave" -> set.remove(inputs[0])
        }
    }

    set.toSortedSet(Comparator.reverseOrder()).forEach { println(it) }
}

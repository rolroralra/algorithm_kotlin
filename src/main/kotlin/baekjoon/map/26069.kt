package baekjoon.map

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val set = mutableSetOf("ChongChong")

    (1..n).forEach {_ ->
        val inputs = br.readLine().split(" ")

        if (set.contains(inputs[0]) or set.contains(inputs[1])) {
            set.addAll(inputs)
        }
    }

    println(set.size)
}

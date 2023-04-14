package baekjoon.bruteforce

fun main() {
    val br = System.`in`.bufferedReader()

    val inputs = br.readLine().split(" ").map { it.toInt() }

    val n = inputs[0]
    val m = inputs[1]

    val list = (1..n).toMutableList()

    (1..m).forEach { _ ->
        val commands = br.readLine().split(" ").map { it.toInt() }

        val i = commands[0]
        val j = commands[1]
        val k = commands[2]

        val copyList = (k..j).union(i until k).map { list[it - 1] }.toList()

        (0.. j - i).forEach {
            list[i + it - 1] = copyList[it]
        }
    }

    println(list.joinToString(" "))
}


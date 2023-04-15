package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    val t = br.readLine().toInt()

    (1..t).forEach { _ ->
        val input = br.readLine().toInt()

        println(getChangeCountList(input).joinToString(" "))
    }
}

private fun getChangeCountList(input: Int): List<Int> {
    val changeList = listOf(25, 10, 5, 1)

    var remainInput = input

    return changeList.map { change ->
        val q = remainInput / change
        remainInput %= change
        q
    }.toList()
}

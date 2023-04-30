package baekjoon.greedy

fun main() {

    val br = System.`in`.bufferedReader()

    val input = br.readLine()

    val indexList = input.indices.filter { input[it] == '+' || input[it] == '-' }.filter { it > 0 }.toMutableList()

    indexList.add(input.length)

    var prevIndex = 0

    val numbers = indexList.map {
        val result = input.substring(prevIndex, it)
        prevIndex = it
        result.toInt()
    }.toMutableList()

    if (input.startsWith("-")) {
        numbers[0] *= -1
    }

    var result = 0
    var isActiveMinus = false
    var sum = 0
    for (value in numbers) {
        if (value < 0) {
            if (isActiveMinus) {
                result += sum
            }

            isActiveMinus = true
            sum = value
        } else if (isActiveMinus) {
            sum -= value
        } else {
            result += value
            isActiveMinus = false
        }
    }

    if (isActiveMinus) {
        result += sum
    }

    println(result)
}

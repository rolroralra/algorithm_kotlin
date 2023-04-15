package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val inputList = (1..n).map { br.readLine().toLong() }

    inputList.forEach {
        println(getPrimeMoreOrEqualThan(it))
    }
}

private fun getPrimeMoreOrEqualThan(input: Long): Long {
    var result = input
    while (true) {
        if (isPrime(result)) {
            return result
        }

        result++
    }
}

private fun isPrime(input: Long): Boolean {
    if (input <= 1) {
        return false
    }

    for (i in 2 until input) {
        if (i * i > input) {
            break
        }

        if (input % i == 0L) {
            return false
        }
    }

    return true
}

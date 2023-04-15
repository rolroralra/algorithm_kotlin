package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    val inputs = br.readLine().split(" ").map { it.toLong() }

    println("${lcm(inputs[0], inputs[1])}")
}

private fun lcm(a: Long, b: Long): Long {
    return a * b / gcd(a, b)
}

private fun gcd(a: Long, b: Long): Long {
    var (_a, _b) = euclideanCalculate(a, b)

    while (_b > 0) {
        _a = euclideanCalculate(_a, _b).also { _b = it.second }.first
    }

    return _a
}

private fun euclideanCalculate(a: Long, b: Long): Pair<Long, Long> = Pair(b, a % b)

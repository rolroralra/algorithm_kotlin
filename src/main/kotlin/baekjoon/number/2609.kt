package baekjoon.number

fun main() {
    val list = readln().split(" ").map { it.toInt() }

    val a = list[0]
    val b = list[1]

    val gcd = gcd(a, b)
    val lcm = lcm(a, b, gcd)
    println(gcd)
    println(lcm)
}

fun gcd(a: Int, b: Int): Int {
    var (_a, _b) = euclideanCalculate(a, b)
    while (_b > 0) {
        val divideAndRemain = euclideanCalculate(_a, _b)
        _a = divideAndRemain.first
        _b = divideAndRemain.second
    }

    return _a
}

fun lcm(a: Int, b: Int, gcd: Int = gcd(a, b)) = a * b / gcd

fun euclideanCalculate(a: Int, b: Int): Pair<Int, Int> = Pair(b, a % b)
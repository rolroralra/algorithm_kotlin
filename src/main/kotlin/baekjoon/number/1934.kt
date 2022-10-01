package baekjoon.number

fun main() {
    val t = readln().toInt()

    for (test_case in 0 until t) {
        val list = readln().split(" ").map { it.toInt() }
        val a = list[0]
        val b = list[1]

        println(lcm(a, b))
    }
}

fun lcm(a: Int, b: Int): Int {
    var (_a, _b) = divideAndRemainder(a, b)
    while (_b > 0) {
        _b = divideAndRemainder(_a, _b).also { _a  = it.first }.second
    }
    return a * b / _a
}

fun divideAndRemainder(a: Int, b: Int) = Pair(b, a % b)

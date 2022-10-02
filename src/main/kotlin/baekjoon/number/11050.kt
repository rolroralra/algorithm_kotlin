package baekjoon.number

fun main() {
    val list = readln().split(" ").map { it.toInt() }

    val n = list[0]
    val k = list[1]

    println(combination(n, k))
}

fun combination(n: Int, k: Int) =
    if (k == 0 || k == n)
        1
    else
        (0 until k).map { n - it }.reduce(Int::times) / (1..k).reduce(Int::times)
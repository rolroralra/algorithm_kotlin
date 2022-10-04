package baekjoon.number

fun main() {
    val inputList = readln().trim().split(" ").map { it.toInt() }

    val n = inputList[0]
    val k = inputList[1]


    val cache = MutableList(n + 1){ MutableList(n + 1) { 0 } }
    println(combination2(n, k, cache))

}

fun combination2(n: Int, k: Int, cache: MutableList<MutableList<Int>>): Int {
    if (cache[n][k] > 0) {
        return cache[n][k]
    }

    if (k == 0 || k == n) {
        cache[n][k] = 1
        return cache[n][k]
    }

    cache[n][k] = (combination2(n - 1, k, cache) + combination2(n - 1, k - 1, cache)) % 10_007
    return cache[n][k]
}

package baekjoon.dp

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    println(fibonacciWithRecursive(n).second)
    println(fibonacciWithDp(n).second)
}

fun fibonacciWithRecursive(n : Int) : Pair<Int, Int> = if (n == 1 || n == 2) 1 to 1 else fibonacciWithRecursive(n - 1).plus(fibonacciWithRecursive(n - 2))

fun fibonacciWithDp(n : Int): Pair<Int, Int> {
    if (n < 3) {
        return 1 to 0
    }

    val cache = IntArray(n + 1)

    cache[1] = 1
    cache[2] = 1

    var count = 0

    for (i in 3..n) {
        cache[i] = cache[i - 1] + cache[i - 2]
        count++
    }

    return cache[n] to count
}

fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = Pair(this.first + other.first, this.second + other.second)
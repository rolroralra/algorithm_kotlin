package leetcode

fun main() {
    println(isPowerOfTwo(16))
}

private fun isPowerOfTwo(n: Int): Boolean {
    return (n > 0) and ((n and (-n)) == n)
}

private fun isPowerOfTwo2(n: Int): Boolean {
    return (n > 0) and (n and (n - 1) == 0)
}

private fun isPowerOfTwo3(n: Int): Boolean {
    if (n == 0) return false
    else if (n < 0) return false

    return generateSequence(n to 0) {
        it.first / 2 to it.first % 2
    }
        .takeWhile { it.second == 0 }
        .last()
        .first == 1
}

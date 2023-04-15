package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    var inputs = br.readLine().split(" ").map { it.toInt() }
    val (a, b) = inputs[0] to inputs[1]

    inputs = br.readLine().split(" ").map { it.toInt() }
    val (c, d) = inputs[0] to inputs[1]

    val numerator = a * d + b * c
    val denominator = b * d

    val gcd = gcd(numerator, denominator)

    println("${numerator / gcd} ${denominator / gcd}")
}


private fun gcd(a: Int, b: Int): Int {
    if (a < b) {
        return gcd(b, a)
    }

    var aCopy = b
    var bCopy = a % b

    while (bCopy > 0) {
        val divisorAndRemainder = (bCopy) to (aCopy % bCopy)
        aCopy = divisorAndRemainder.first
        bCopy = divisorAndRemainder.second
    }

    return aCopy
}

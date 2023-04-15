package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    while (true) {
        val inputs = br.readLine().split(" ").map { it.toInt() }
        val a = inputs[0]
        val b = inputs[1]
        val c = inputs[2]

        if (a == 0 && b == 0 && c == 0) {
            break
        }

        println(getTriangleType(a, b, c))
    }
}

fun getTriangleType(a: Int, b: Int, c: Int): String {
    if (isInvalidTriangle(a, b, c)) {
        return "Invalid"
    }

    return when (listOf(a, b, c).distinct().count()) {
        1 -> "Equilateral"
        2 -> "Isosceles"
        3 -> "Scalene"
        else -> "Invalid"
    }
}

fun isInvalidTriangle(a: Int, b: Int, c: Int): Boolean {
    return !isValidTriangle(a, b, c)
}

fun isValidTriangle(a: Int, b: Int, c: Int): Boolean {
    val edgeLengthList = listOf(a, b, c).sorted()

    val sum = edgeLengthList.take(2).sum()
    val max = edgeLengthList.maxOf { it }

    return sum > max
}

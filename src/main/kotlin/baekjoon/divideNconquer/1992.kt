package baekjoon.divideNconquer

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val list = (1..n).map { br.readLine() }

    println(divideAndConquer(0, 0, n, list))
}

private fun divideAndConquer(row: Int, col: Int, n: Int, list: List<String>): String {
    if (n <= 1) {
        return list[row].get(col).toString()
    }

    val halfN = n / 2

    val leftTop = divideAndConquer(row, col, halfN, list)
    val rightTop = divideAndConquer(row, col + halfN, halfN, list)
    val leftBottom = divideAndConquer(row + halfN, col, halfN, list)
    val rightBottom = divideAndConquer(row + halfN, col + halfN, halfN, list)

    val setOfDivide = setOf(leftTop, rightTop, leftBottom, rightBottom)
    if (setOfDivide.size == 1 && setOfDivide.first() in setOf("0", "1")) {
        return setOfDivide.first()
    }

    return "(${leftTop}${rightTop}${leftBottom}${rightBottom})"
}

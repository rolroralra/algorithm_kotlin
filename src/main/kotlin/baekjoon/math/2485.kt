package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val inputList = (1..n).map { br.readLine().toInt() }

    val intervalList = (1..inputList.lastIndex).map { inputList[it] - inputList[it - 1] }

    val gcd = gcd(intervalList)

    val minPoint = inputList.minOf { it }
    val maxPoint = inputList.maxOf { it }


    val result = (maxPoint - minPoint) / gcd - inputList.size + 1

    println(result)
}

private fun gcd(x: Int, y: Int) : Int {
    var (xCopy, yCopy) = euclideanCalculate(x, y)

    while (yCopy > 0) {
        xCopy = euclideanCalculate(xCopy, yCopy).also { yCopy = it.second }.first
    }

    return xCopy
}

private fun gcd(list: List<Int>): Int {
    if (list.isEmpty()) {
        return 1
    } else if (list.size == 1) {
        return list[0]
    }

    var gcd = gcd(list[0], list[1])

    for (i in 2..list.lastIndex) {
        if (gcd == 1) {
            break
        }

        gcd = gcd(gcd,  list[i])
    }

    return gcd
}

private fun euclideanCalculate(x: Int, y: Int): Pair<Int, Int> = Pair(y, x % y)

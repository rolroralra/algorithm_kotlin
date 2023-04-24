package baekjoon.divideNconquer

fun main() {
    val n = readln().trim().toInt()

    val graph = (0 until n).map { readln().trim().split(" ").map { it.toInt() } }

    val (a, b) = divideAndConquer(0, 0, n, graph)
    println(a)
    println(b)
}

private fun divideAndConquer(x: Int, y: Int, n: Int, graph: List<List<Int>>) : Pair<Int, Int>{
    if (n == 1) {
        return if (graph[x][y] == 0) Pair(1, 0) else Pair(0, 1)
    }

    val value = graph[x][y]
    var isFailed = false
    for (i in x until x + n) {
        if (isFailed) {
            break
        }

        for (j in y until y + n) {
            if (graph[i][j] != value) {
                isFailed = true
                break
            }
        }
    }

    if (!isFailed) {
        return if (graph[x][y] == 0) Pair(1, 0) else Pair(0, 1)
    }

    val nextSize = n / 2
    var result  = divideAndConquer(x, y, nextSize, graph)
    result += divideAndConquer(x + nextSize, y, nextSize, graph)
    result += divideAndConquer(x, y + nextSize, nextSize, graph)
    result += divideAndConquer(x + nextSize , y + nextSize, nextSize, graph)

    return result
}

operator fun Pair<Int, Int>.plus(pair: Pair<Int, Int>) = Pair(this.first + pair.first, this.second + pair.second)

package baekjoon.geometry

class Circle(val x: Int, val y: Int, val radius: Int) {
    fun isIn(x: Int, y: Int) = (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y) < radius * radius
    fun isOut(x: Int, y: Int) = (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y) > radius * radius
}


fun main() {
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    for (testCase in 1..t) {
        val (x1, y1, x2, y2) = br.readLine().split(" ").map { it.toInt() }

        val n = br.readLine().toInt()
        val circleList =
            (1..n).map { br.readLine().split(" ").map { it.toInt() } }.map { Circle(it[0], it[1], it[2]) }
                .toList()

        var `in` = 0
        var out = 0
        for (circle in circleList) {
            if (circle.isIn(x1, y1) && circle.isOut(x2, y2)) {
                out++
            } else if (circle.isOut(x1, y1) && circle.isIn(x2, y2)) {
                `in`++
            }
        }

        println("${`in` + out}")
    }
}

package baekjoon.geometry

class Circle2(val x: Int, val y: Int, val radius: Int) {
    fun isIn(x: Int, y: Int) = (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y) <= radius * radius
}

class Rectangle(val x: Int, val y: Int, val width: Int, val height: Int) {
    fun isIn(x: Int, y: Int) = x in (this.x..this.x + this.width) && y in (this.y..this.y + this.height)
}

fun main() {
    val br = System.`in`.bufferedReader()

    val (w, h, x, y, p) = br.readLine().trim().split(" ").map { it.toInt() }

    val rectangle = Rectangle(x, y, w, h)
    val r = h / 2
    val circleList = listOf(Circle2(x, y + r, r), Circle2(x + w, y + r, r))

    var result = 0
    for (i in 1..p) {
        val (a, b) = br.readLine().trim().split(" ").map { it.toInt() }
        if (rectangle.isIn(a, b) || circleList.any { it.isIn(a, b) }) {
            result++
        }
    }

    println(result)
}
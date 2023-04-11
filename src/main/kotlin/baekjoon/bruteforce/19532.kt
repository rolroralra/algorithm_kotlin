package baekjoon.bruteforce

fun main() {
    val br = System.`in`.bufferedReader()
    val inputs = br.readLine().split(" ").map { it.toInt() }

    val a = inputs[0]
    val b = inputs[1]
    val c = inputs[2]
    val d = inputs[3]
    val e = inputs[4]
    val f = inputs[5]



    val x = (c * e - b * f) / (a * e - b * d)


    val y = if (b != 0) ((-a) * x + c) / b else ((-d) * x + f) / e

    println("$x $y")
}

package baekjoon.number

fun main() {
    readln()
    val list = readln().split(" ").map{ it.toInt() }.sorted()
    println(list.first().toInt().times(list.last().toInt()))
}

package baekjoon.string

    fun main() {
        val br = System.`in`.bufferedReader()

        val testCount = br.readLine().toInt()

        (1..testCount).forEach { _ ->
            val str = br.readLine().trim()

            println("${str.first()}${str.last()}")
        }
    }

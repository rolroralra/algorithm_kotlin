package baekjoon.queue

import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()

    val t = br.readLine().toInt()


    (1..t).forEach { _ ->
        val commands = br.readLine().toCharArray()

        val n = br.readLine().toInt()

        val inputListString = br.readLine()

        val inputList = if (n == 0) LinkedList() else LinkedList(
            inputListString.trimStart('[').trimEnd(']').split(",")
        )

        var isError = false
        var isReverse = false

        for (command in commands) {
            if (isError) {
                break
            }

            when (command) {
                'R' -> isReverse = !isReverse
                'D' -> if (inputList.isEmpty()) isError = true else if (isReverse) inputList.pollLast() else inputList.pollFirst()
            }
        }

        if (isReverse) {
            inputList.reverse()
        }

        println(if (isError) "error" else inputList.joinToString(",", "[", "]"))
    }
}

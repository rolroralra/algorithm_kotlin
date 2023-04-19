package baekjoon.queue

import java.util.LinkedList
import java.util.PriorityQueue

fun main() {
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    (1..t).forEach { _ ->
        val inputs = br.readLine().split(" ").map { it.toInt() }

        val documentPriorityList = br.readLine().split(" ").map { it.toInt() }.toList()

        val targetPriority = documentPriorityList[inputs[1]]

        val priorityQueue = PriorityQueue(documentPriorityList.size, Comparator.reverseOrder<Int>())
        documentPriorityList.forEach { priorityQueue.add(it) }

        val queue = LinkedList(documentPriorityList)

        var result = 0
        val n = inputs[0]
        var index = inputs[1]
        while (queue.isNotEmpty()) {
            val priority = queue.poll()
            val currentBestPriority = priorityQueue.peek()

            if (currentBestPriority > priority) {
                queue.add(priority)
                index = (index - 1 + queue.size) % queue.size
                continue
            } else if (currentBestPriority == priority) {
                priorityQueue.poll()
            }

            if (targetPriority == priority && index == 0){
                result = inputs[0] - queue.size
                break
            }

            index = (index - 1 + queue.size) % queue.size
        }

        println(result)
    }
}

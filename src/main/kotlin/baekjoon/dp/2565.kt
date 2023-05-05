package baekjoon.dp

import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val list = mutableListOf<Pair<Int, Int>>()
    repeat(n) {
        br.readLine().split(" ")
            .map { it.toInt() }
            .let {
                list.add(it[0] to it[1])
            }
    }

    list.sortBy { it.first }

    val result = n - lis(list.map { it.second }).size

    println(result)
}

private fun lis(inputList: List<Int>): List<Int> {
    val sortingList = mutableListOf<Pair<Int, Int>>()
    val indices = MutableList(inputList.size) { -1 }
    val prevIndices = MutableList(inputList.size) { -1 }

    for ((i, value) in inputList.withIndex()) {
        val orderIndex = lowerBound(sortingList.map { it.second }, value).let { if (it < 0) -(it + 1) else it }

        if (orderIndex in sortingList.indices) {
            sortingList[orderIndex] = i to value
        } else {
            sortingList.add(i to value)
        }

        indices[orderIndex] = i
        prevIndices[i] = if (orderIndex > 0) indices[orderIndex - 1] else -1
    }

    var currIndex = indices[sortingList.size - 1]
    val stack = Stack<Int>()
    while (currIndex in inputList.indices) {
        stack.push(inputList[currIndex])

        currIndex = prevIndices[currIndex]
    }

    val result = mutableListOf<Int>()
    while (stack.isNotEmpty()) {
        result.add(stack.pop())
    }

    return result
}

private fun lowerBound(list: List<Int>, target: Int, startIndexInclusive: Int = 0, endIndexExclusive: Int = list.size): Int {
    var start = startIndexInclusive
    var end = endIndexExclusive - 1

    var result: Int = endIndexExclusive

    while (start <= end) {
        val mid = (start + end) / 2

        if (list[mid] >= target) {
            end = mid - 1
            result = mid
        } else if (list[mid] < target) {
            start = mid + 1
        }
    }

    return if (result in (startIndexInclusive until endIndexExclusive)) result else -(result + 1)
}

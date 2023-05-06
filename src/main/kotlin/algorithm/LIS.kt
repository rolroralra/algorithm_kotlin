package algorithm

import java.util.*
import kotlin.Comparator

fun main() {
    println(getLIS(listOf(1, 3, 2, 4, 5 ,3, 2, 1))) // [1, 2, 4, 5]
    println(getLengthOfLIS(listOf(1, 3, 2, 4, 5 ,3, 2, 1))) // 4
}

/**
 * @deprecated use [algorithm.getLengthOfLIS] instead
 */
@Deprecated("instead of using algorithm.getLengthOfLIS")
private fun <T: Comparable<T>> getLengthOfLISWithN2(arr: List<T>, comparator: java.util.Comparator<T> = Comparator.naturalOrder()): Int {
    val dp = MutableList(arr.size) { 1 }

    for (i in 1 until arr.size) {
        for (j in 0 until i) {
            if (comparator.compare(arr[i], arr[j]) > 0) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
    }

    return dp.maxOf { it }
}

/**
 * @deprecated use [algorithm.getLIS] instead
 */
@Deprecated("instead of using algorithm.getLIS")
private fun <T: Comparable<T>> getLISWithN2(arr: List<T>, comparator: java.util.Comparator<T> = Comparator.naturalOrder()): List<T> {
    val dp = MutableList(arr.size) { 1 }

    val lis = mutableListOf(0 to arr[0])
    val prevArrayIndices = MutableList(arr.size) { -1 }

    for (i in 1 until arr.size) {
        val j = (0 until i)
            .filter { comparator.compare(arr[i], arr[it]) > 0 }
            .maxByOrNull { dp[it] + 1 } ?: continue

        dp[i] = dp[j] + 1
        val lisIndex = dp[i] - 1

        if (lisIndex in lis.indices) {
            lis[lisIndex] = i to arr[i]
        } else {
            lis.add(i to arr[i])
        }

        prevArrayIndices[i] = if (lisIndex > 0) lis[lisIndex - 1].first else -1
    }

    var currIndex = lis.last().first
    val stack = Stack<T>()
    while (currIndex in arr.indices) {
        stack.push(arr[currIndex])

        currIndex = prevArrayIndices[currIndex]
    }

    val result = mutableListOf<T>()
    while (stack.isNotEmpty()) {
        result.add(stack.pop())
    }

    return result
}

/**
 * returns the length of longest increasing subsequence
 */
fun <T: Comparable<T>> getLengthOfLIS(arr: List<T>, comparator: java.util.Comparator<T> = Comparator.naturalOrder()): Int {
    return logicForLIS(arr, comparator = comparator).first.size
}

/**
 * returns the length of longest increasing subsequence
 */
fun <T: Comparable<T>> getLIS(arr: List<T>, comparator: java.util.Comparator<T> = Comparator.naturalOrder()): List<T> {
    val (lis, prevArrayIndices) = logicForLIS(arr, comparator = comparator)

    return getLIS(lis, arr, prevArrayIndices)
}

private fun <T: Comparable<T>> logicForLIS(arr: List<T>, comparator: java.util.Comparator<T> = Comparator.naturalOrder()): Pair<List<Pair<Int, T>>, List<Int>> {
    val lis = mutableListOf<Pair<Int, T>>()
    val prevArrayIndices = MutableList(arr.size) { -1 }

    for ((i, value) in arr.withIndex()) {
        var lisIndex = lowerBound(lis.map { it.second }, value, comparator = comparator)

        lisIndex = if (lisIndex < 0) -(lisIndex + 1) else lisIndex

        if (lisIndex in lis.indices) {
            lis[lisIndex] = i to value
        } else {
            lis.add(i to value)
        }

        prevArrayIndices[i] = if (lisIndex > 0) lis[lisIndex - 1].first else -1
    }

    return lis to prevArrayIndices
}

private fun <T> getLIS(lis: List<Pair<Int, T>>, arr: List<T>, prevArrayIndices: List<Int>): List<T> {
    var currIndex = lis.last().first
    val stack = Stack<T>()
    while (currIndex in arr.indices) {
        stack.push(arr[currIndex])

        currIndex = prevArrayIndices[currIndex]
    }

    val result = mutableListOf<T>()
    while (stack.isNotEmpty()) {
        result.add(stack.pop())
    }

    return result
}


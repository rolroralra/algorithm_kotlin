package algorithm

import java.util.*
import kotlin.Comparator

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

// TODO: implementation
private fun <T: Comparable<T>> getLengthOfLIS(arr: List<T>, comparator: java.util.Comparator<T> = Comparator.naturalOrder()): Int {
    val dp = MutableList(arr.size) { 1 }

    for (i in arr.indices) {
        for (j in 0 until i) {
            if (comparator.compare(arr[i], arr[j]) > 0) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
    }

    return dp.maxOf { it }
}

// TODO: implementation
private fun <T: Comparable<T>> getLIS(arr: List<T>, comparator: java.util.Comparator<T> = Comparator.naturalOrder()): List<T> {
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


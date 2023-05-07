package algorithm

fun main() {
    knapsack(listOf(6, 4, 3, 5), listOf(13, 8, 6, 12), 12)
        .let(::println)  // 14
}

fun knapsack(weightList: List<Int>, valueList: List<Int>, boundedWeight: Int, isAllowedDuplication: Boolean = false): Int {
    return if (isAllowedDuplication) {
        knapsackWithAllowedDuplication(weightList, valueList, boundedWeight)
    } else {
        knapsackWithoutAllowedDuplication(weightList, valueList, boundedWeight)
    }
}

private fun knapsackWithAllowedDuplication(weightList: List<Int>, valueList: List<Int>, boundedWeight: Int): Int {
    val dp = MutableList(2) { MutableList(boundedWeight + 1) { 0 } }

    for (i in weightList.indices) {
       val weight = weightList[i]
       val value = valueList[i]

       for (w in 1..boundedWeight) {
           if (w - weight >= 0) {
               dp[1][w] = maxOf(dp[0][w], dp[1][w - weight] + value)
           } else {
               dp[1][w] = dp[0][w]
           }
       }

       dp[1] = dp.also { it[0] = it[1] }[0] // swap
    }

    return dp[0][boundedWeight]
}

private fun knapsackWithoutAllowedDuplication(weightList: List<Int>, valueList: List<Int>, boundedWeight: Int): Int {
    val dp = MutableList(boundedWeight + 1) { 0 }

    for (i in weightList[0]..boundedWeight) {
        dp[i] = valueList[0]
    }

    for (i in 1 until weightList.size) {
        val weight = weightList[i]
        val value = valueList[i]

        for (j in boundedWeight downTo 1) {
            if (j - weight >= 0) {
                dp[j] = maxOf(dp[j], dp[j - weight] + value)
            }
        }
    }

    return dp[boundedWeight]
}

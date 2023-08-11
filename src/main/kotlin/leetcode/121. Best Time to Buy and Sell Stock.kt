package leetcode

import kotlin.math.max
import kotlin.math.min

fun main() {
    check(maxProfit(intArrayOf(7,1,5,3,6,4)) == 5)
    check(maxProfit(intArrayOf(7,6,4,3,1)) == 0)
}

@Suppress("UNUSED")
private fun maxProfit(prices: IntArray): Int {
    var minPrice: Int? = null
    var result = 0
    for (index in prices.indices) {
        val currPrice = prices[index]
        minPrice = if (minPrice == null) currPrice else min(minPrice, currPrice)

        result = max(result, currPrice - minPrice)
    }

    return result
}

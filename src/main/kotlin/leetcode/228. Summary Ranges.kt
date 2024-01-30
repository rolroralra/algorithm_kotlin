package leetcode

fun main() {
    summaryRanges(intArrayOf(0, 1, 2, 4, 5, 7)).also { println(it) }
    summaryRanges(intArrayOf(0, 2, 3, 4, 6, 8, 9)).also { println(it) }
    summaryRanges(intArrayOf()).also { println(it) }
    summaryRanges(intArrayOf(0, 9)).also { println(it) }

}

private fun summaryRanges(nums: IntArray): List<String> {
    if (nums.isEmpty()) {
        return emptyList()
    }

    var startIndex = 0
    var endIndex = startIndex
    var pivotIndex = startIndex + 1

    val result = mutableListOf<String>()

    while (pivotIndex < nums.size) {
        if (nums[pivotIndex - 1] + 1 != nums[pivotIndex]) {
            result.add(toSummaryRangeDescription(nums[startIndex], nums[endIndex]))
            startIndex = pivotIndex
            endIndex = startIndex
            pivotIndex++
            continue
        }

        endIndex = pivotIndex

        pivotIndex++
    }

    result.add(toSummaryRangeDescription(nums[startIndex], nums[endIndex]))

    return result
}

private fun toSummaryRangeDescription(startValue: Int, endValue: Int): String {
    return (startValue to endValue).toSummaryRangeDescription()
}

private fun Pair<Int, Int>.toSummaryRangeDescription() : String {
    return if (first == second) {
        "$first"
    } else {
        "$first->$second"
    }
}


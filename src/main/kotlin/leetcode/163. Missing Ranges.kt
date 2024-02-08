package leetcode

fun main() {
    findMissingRanges(intArrayOf(0, 1, 3, 50, 75), 0, 99).also { println(it) }
    findMissingRanges(intArrayOf(-1), -1, -1).also { println(it) }
    findMissingRanges(intArrayOf(), 1, 1).also { println(it) }

}

private fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    var startIndex = lower

    for (missingNumber in nums) {
        val lastIndex = missingNumber - 1

        if (startIndex > lastIndex) {
            startIndex = missingNumber + 1
            continue
        }

        result.add(listOf(startIndex, lastIndex))
        startIndex = missingNumber + 1
    }

    if (startIndex <= upper) {
        result.add(listOf(startIndex, upper))
    }

    return result
}



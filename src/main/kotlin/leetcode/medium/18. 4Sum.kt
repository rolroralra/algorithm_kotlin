package leetcode.medium

fun main() {
    println(fourSum(intArrayOf(1, 0, -1, 0, -2, 2), 0))
    println(fourSum(intArrayOf(2, 2, 2, 2, 2), 8))
    println(fourSum(intArrayOf(1000000000,1000000000,1000000000,1000000000), -294967296))
}

fun fourSum(nums: IntArray, target: Int) : List<List<Int>> {
    val list = nums.sorted()

    val result = mutableSetOf<List<Int>>()

    for (i in 2 .. list.size - 2) {
        for (j in (i + 1) until list.size) {
            result.addAll(twoSum(list, 0, i - 1, target.toLong() - list[i] - list[j], list[i] to list[j]))
        }
    }

    return result.toList()
}

private fun twoSum(nums: List<Int>, startIndex: Int, endInclusiveIndex: Int, target: Long, pair: Pair<Int, Int>): List<List<Int>> {
    var left = startIndex
    var right = endInclusiveIndex

    val result = mutableListOf<List<Int>>()

    while (left < right) {
        val sum = nums[left].toLong() + nums[right]


        if (sum < target) {
            left++
            continue
        } else if (sum > target) {
            right--
            continue
        }

        result.add(listOf(nums[left], nums[right], pair.first, pair.second))
        left++
        right--
    }

    return result
}
package leetcode

fun main() {
    check(singleNumber(intArrayOf(4, 1, 2, 1, 2)) == 4)
    check(singleNumber(intArrayOf(2, 2, 1)) == 1)
    check(singleNumber(intArrayOf(1)) == 1)
}

private fun singleNumber(nums: IntArray): Int {
    return nums
        .groupBy { it }
        .filter { it.value.size == 1 }
        .map { it.key }
        .first()
}

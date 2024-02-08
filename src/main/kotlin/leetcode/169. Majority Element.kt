package leetcode

fun majorityElement(nums: IntArray): Int {
    return nums.toList()
        .groupingBy { it }
        .eachCount()
        .maxByOrNull { it.value }?.key
        ?: throw Exception()
}

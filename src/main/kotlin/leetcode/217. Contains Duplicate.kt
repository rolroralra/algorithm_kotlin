package leetcode

fun main() {
    check(containsDuplicate(intArrayOf(1,2,3,1)))
    check(containsDuplicate(intArrayOf(1,2,3,4)).not())
    check(containsDuplicate(intArrayOf(1,1,1,3,3,4,3,2,4,2)))

}

private fun containsDuplicate(nums: IntArray): Boolean {
    return nums.distinct().size != nums.size
}

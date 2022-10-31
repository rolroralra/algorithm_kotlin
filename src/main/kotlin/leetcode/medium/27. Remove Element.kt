package leetcode.medium

fun main() {
    val array = intArrayOf(3, 2, 2, 3)
    val count = removeElement(array, 3)
    println(count)
    println(array.slice(0 until array.size - count))
}

fun removeElement(nums: IntArray, `val`: Int): Int {
    val resultList = nums.filter { it != `val` }

    for ((i , value) in resultList.withIndex()) {
        nums[i] = value
    }

    return resultList.size
}
package leetcode

fun main() {
    println(searchRange(intArrayOf(5,7,7,8,8,10), 8).contentToString())
    println(searchRange(intArrayOf(5,7,7,8,8,10), 6).contentToString())
    println(searchRange(intArrayOf(), 0).contentToString())

}

fun searchRange(nums: IntArray, target: Int): IntArray {
    return intArrayOf(nums.indexOfFirst { it == target }, nums.lastIndexOf(target))
}
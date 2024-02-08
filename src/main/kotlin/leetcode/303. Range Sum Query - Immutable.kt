package leetcode

fun main() {
    NumArray2(intArrayOf(-2, 0, 3, -5, 2, -1)).apply {
        println(sumRange(0, 2)) // 1
        println(sumRange(2, 5)) // -1
        println(sumRange(0, 5)) // -3
    }
}

private class NumArray2(nums: IntArray) {
    private val sums = IntArray(nums.size) { 0 }.apply {
        nums.withIndex().forEach { (index, num) ->
            this[index] = this.getOrElse(index - 1) { 0 } + num
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        return sums[right] - sums.getOrElse(left - 1) { 0 }
    }
}

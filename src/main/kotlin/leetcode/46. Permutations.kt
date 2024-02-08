package leetcode

fun main() {
    println(
        permute(intArrayOf(1, 2, 3)).joinToString(",", "[", "]") {
            it.toString()
        }
    )
}

private fun permute(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    permute(nums, 0, result)
    return result
}

private fun permute(nums: IntArray, currIndex: Int, result: MutableList<List<Int>>) {
    if (currIndex == nums.size) {
        result.add(nums.toList())
        return
    }

    for (nextIndex in currIndex..nums.lastIndex) {
        nums.swap(nextIndex, currIndex)
        permute(nums, currIndex + 1, result)
        nums.swap(nextIndex, currIndex)
    }
}

private fun IntArray.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

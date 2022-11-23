package leetcode

fun main() {
    println(search(intArrayOf(4,5,6,7,0,1,2), 0))
    println(search(intArrayOf(4,5,6,7,0,1,2), 3))
    println(search(intArrayOf(1), 0))

}

private fun search(nums: IntArray, target: Int): Int {
    var k = -1
    for (i in nums.indices.reversed()) {
        if (k == -1 || nums[i] < nums[k]) {
            k = i
            continue
        }

        break
    }

    return (nums.slice((0 until k)) + nums.slice(k until nums.size)).indexOf(target)
}
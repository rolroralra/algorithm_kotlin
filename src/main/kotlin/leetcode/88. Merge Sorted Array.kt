package leetcode

fun main() {
    merge(intArrayOf(1,2,3,0,0,0), 3, intArrayOf(2,5,6), 3)
    merge(intArrayOf(1), 1, intArrayOf(), 0)
    merge(intArrayOf(0), 0, intArrayOf(1), 1)

}

private fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
    var mIndex = 0
    var nIndex = 0
    var index = 0

    val nums1Copy = nums1.copyOf()

    while (mIndex < m && nIndex < n) {
        if (nums1Copy[mIndex] <= nums2[nIndex]) {
            nums1[index++] = nums1Copy[mIndex++]
        } else {
            nums1[index++] = nums2[nIndex++]
        }
    }

    while (mIndex < m) {
        nums1[index++] = nums1Copy[mIndex++]
    }

    while (nIndex < n) {
        nums1[index++] = nums2[nIndex++]
    }

    println(nums1.contentToString())
}

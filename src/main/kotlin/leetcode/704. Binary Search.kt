package leetcode

private fun search(nums: IntArray, target: Int): Int {
    val result = nums.binarySearch(target)
    return if (result >= 0) result else -1
}

fun latestVersion(versions: List<String>): String {
    versions.sortedWith(compareBy())

    return ""
}

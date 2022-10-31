package leetcode
fun isBadVersion(version: Int) : Boolean {
    return false
}

fun firstBadVersion(n: Int) : Int {
    var start = 1L
    var end = n.toLong()

    var lowerBound = (n + 1).toLong()
    while (start <= end) {
        val mid = (start + end) / 2

        if (isBadVersion(mid.toInt())) {
            end = mid - 1
            lowerBound = mid
        } else {
            start = mid + 1
        }
    }

    return lowerBound.toInt()
}
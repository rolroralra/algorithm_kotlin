package leetcode.medium

fun main() {
    println(longestPalindrome("babad"))
    println(longestPalindrome("cbbd"))

}

fun longestPalindrome(s: String): String {
    val length = s.length
    var maxStart = 0
    var maxEnd = 0
    var maxLength = 1
    for (i in 0 until length) {
        for (j in i + maxLength until length) {
            val newLength = j - i + 1
            if (newLength < maxLength) {
                continue
            }
            if (checkPalindrome(s, i, j)) {
                maxLength = newLength
                maxStart = i
                maxEnd = j
            }
        }
    }
    return s.substring(maxStart, maxEnd + 1)
}

fun checkPalindrome(s: String, startInclusive: Int, endInclusive: Int): Boolean {
    var start = startInclusive
    var end = endInclusive
    val midIndex = (start + end) / 2
    while (midIndex in start..end) {
        if (s[start++] != s[end--]) {
            return false
        }
    }
    return true
}

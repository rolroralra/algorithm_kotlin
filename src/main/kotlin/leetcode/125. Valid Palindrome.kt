package leetcode

fun main() {
    check(isPalindrome("A man, a plan, a canal: Panama"))
    check(!isPalindrome("race a car"))
    check(isPalindrome(" "))
    check(!isPalindrome("0P"))
}

private fun isPalindrome(s: String): Boolean {
    val stringFilteredAlphabetically = s.filterAlphabetically().lowercase()
    return stringFilteredAlphabetically == stringFilteredAlphabetically.reversed()
}

private fun String.filterAlphabetically(): String {
    return this.filter { it.isLetterOrDigit() }
}


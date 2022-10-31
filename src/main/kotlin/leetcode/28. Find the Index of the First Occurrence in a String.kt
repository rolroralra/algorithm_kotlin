package leetcode

fun main() {
    println(strStr("sadbutsad", "sad"))
    println(strStr("leetcode", "leeto"))
}

fun strStr(haystack: String, needle: String): Int {
    return haystack.indexOf(needle)
}
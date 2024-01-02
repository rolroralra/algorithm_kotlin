package leetcode

fun main() {
    val input = "123456789".toCharArray()
    reverseString(input)
    println(input)
}

private fun reverseString(s: CharArray): Unit {
    var startIndex = 0
    var endIndex = s.lastIndex

    repeat(s.size / 2) {
        s[startIndex] = s[endIndex].also { s[endIndex] = s[startIndex] }
        startIndex++
        endIndex--
    }
}

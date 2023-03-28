package leetcode

fun main() {
    println(lengthOfLastWord("Hello World"))
    println(lengthOfLastWord("   fly me   to   the moon  "))
    println(lengthOfLastWord("luffy is still joyboy"))
}

fun lengthOfLastWord(s: String): Int =
    s.trimEnd(' ')
        .split(" ")
        .last().length


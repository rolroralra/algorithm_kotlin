package leetcode.medium

import java.lang.Integer.max
import java.util.*

fun main() {
    println(lengthOfLongestSubstring("abcabcbb"))
    println(lengthOfLongestSubstring("bbbbbb"))
    println(lengthOfLongestSubstring("pwwkew"))
    println(lengthOfLongestSubstring(""))

}

fun lengthOfLongestSubstring(s: String): Int {
    val queue = LinkedList<Char>()

    var result = 0

    for (index in s.indices) {
        val character = s[index]

        if (queue.contains(s[index])) {
            while (queue.isNotEmpty()) {
                if (queue.poll() == s[index]) {
                    break
                }
            }
        }

        queue.offer(character)
        result = max(result, queue.size)
    }
    return result
}

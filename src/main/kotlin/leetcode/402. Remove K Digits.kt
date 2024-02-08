package leetcode

import java.lang.StringBuilder
import java.util.Stack


fun main() {
    check(removeKdigits("1432219", 3) == ("1219"))
    check(removeKdigits("10200", 1) == ("200"))
    check(removeKdigits("10", 2) == ("0"))
    check(removeKdigits("9", 1) == ("0"))

}

private fun removeKdigits(num: String, k: Int): String {
    val stack = Stack<Char>()

    var removedCount = 0
    num.toCharArray().forEach { digit ->
        while (stack.isNotEmpty() && stack.peek() > digit && removedCount < k) {
            stack.pop()
            removedCount++
        }
        stack.push(digit)
    }

    while (removedCount < k) {
        stack.pop()
        removedCount++
    }

    val result = StringBuilder()

    while (stack.isNotEmpty()) {
        result.insert(0, stack.pop())
    }

    return result.toString().trimStart('0').ifBlank { "0" }
}

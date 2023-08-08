package leetcode

import java.lang.StringBuilder

fun main() {
    check(countAndSay(1) == "1")
    check(countAndSay(2) == "11")
    check(countAndSay(3) == "21")
    check(countAndSay(4) == "1211")
    check(countAndSay(5) == "111221")
    check(countAndSay(6) == "312211")
}

private fun countAndSay(n: Int): String {
    if (n <= 1) {
        return "1"
    }

    val sb = StringBuilder()

    var prevCharacter: Char? = null
    var count = 0

    val prevResult = countAndSay(n - 1)

    for (i in prevResult.indices) {
        if (prevCharacter != null && prevCharacter != prevResult[i]) {
            sb.append(count).append(prevCharacter)
            count = 1
        } else {
            count++
        }

        prevCharacter = prevResult[i]
    }

    sb.append(count).append(prevCharacter)

    return sb.toString()
}

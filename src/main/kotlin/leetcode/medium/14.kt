package leetcode.medium

import kotlin.math.min

fun main() {
    println(`14`().longestCommonPrefix(arrayOf("flower", "flow", "flight")))
    println(`14`().longestCommonPrefix(arrayOf("dog", "raceacar", "car")))

}

class `14` {
    fun longestCommonPrefix(strs: Array<String>): String {
        var minLength = Int.MAX_VALUE
        for (str in strs) {
            minLength = min(minLength, str.length)
        }

        var prefixIndex = -1
        for (i in 0 until minLength) {
            if (strs.map { it[i] }.toSet().size > 1) {
                break
            }

            prefixIndex = i
        }

        return strs.first().substring(0, prefixIndex + 1)
    }
}
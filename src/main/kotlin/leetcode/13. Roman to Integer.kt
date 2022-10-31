package leetcode

fun main() {
    println(romanToInt("III"))
    println(romanToInt("LVIII"))
    println(romanToInt("MCMXCIV"))

}
fun romanToInt(s: String): Int {
    val valueMap = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
    val prefixMap = mapOf('I' to setOf('V', 'X'), 'X' to setOf('L', 'C'), 'C' to setOf('D', 'M'))

    var index = 0
    var result = 0
    while (index < s.length) {
        val c = s[index]

        if ((c == 'I' || c == 'X' || c == 'C') && index + 1 < s.length && s[index + 1] in (prefixMap[c] ?: emptySet())) {
            result -= valueMap[c]!!
        } else {
            result += valueMap[c]!!
        }

        index++
    }

    return result
}

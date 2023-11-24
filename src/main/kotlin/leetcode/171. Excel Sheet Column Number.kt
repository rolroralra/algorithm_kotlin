package leetcode

fun main() {
    println(titleToNumber("A")) // 1
    println(titleToNumber("AB")) // 28
    println(titleToNumber("ZY")) // 701
}

private fun titleToNumber(columnTitle: String): Int {
    return columnTitle.reversed().toCharArray()
        .map { it.code - 'A'.code + 1 }
        .withIndex()
        .sumOf { ('Z'.code - 'A'.code + 1).pow(it.index) * it.value }
}

private fun Int.pow(value: Int) : Int {
    return (1..value)
        .map { this }
        .ifEmpty { listOf(1) }
        .reduce(Int::times)
}

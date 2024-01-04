package leetcode

fun main() {
    check(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", arrayOf("hit")) == "ball")
    check(mostCommonWord("a.", arrayOf()) == "a")
}

const val SPACE_DELIMITER = " "
const val PARAGRAPH_SYMBOLS = "!?',;."
val PARAGRAPH_SYMBOLS_REGEX = Regex("[${SPACE_DELIMITER}${PARAGRAPH_SYMBOLS}]")

private fun mostCommonWord(paragraph: String, banned: Array<String>): String {
    return paragraph
        .split(PARAGRAPH_SYMBOLS_REGEX)
        .filter { it.isNotBlank() }
        .map { it.lowercase() }
        .groupingBy { it }
        .eachCount()
        .filterKeys { it !in banned }
        .maxWithOrNull(compareBy { it.value })
        ?.key ?: ""
}

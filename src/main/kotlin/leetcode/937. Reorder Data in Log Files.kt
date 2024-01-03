package leetcode

fun main() {
    reorderLogFiles(
        arrayOf(
            "dig1 8 1 5 1",
            "let1 art can",
            "dig2 3 6",
            "let2 own kit dig",
            "let3 art zero"
        )
    ).forEach { println(it) }

    println()

    reorderLogFiles(
        arrayOf(
            "a1 9 2 3 1",
            "g1 act car",
            "zo4 4 7",
            "ab1 off key dog",
            "a8 act zoo"
        )
    ).forEach { println(it) }
}

private fun reorderLogFiles(logs: Array<String>): Array<String> {
    val digitLogs = logs
        .map { Log.from(it) }
        .filter { it.isDigitLog() }

    val letterLogs = logs
        .map { Log.from(it) }
        .filter { it.isDigitLog().not() }
        .sortedWith(compareBy({ it.contents }, { it.id }))

    return (letterLogs + digitLogs).map { it.toString() }.toTypedArray()
}

private class Log private constructor(val id: String, val contents: LogContents) {
    fun isDigitLog(): Boolean {
        return contents.containsAllDigitLog()
    }

    override fun toString(): String {
        return "${this.id} ${this.contents}"
    }

    companion object {
        fun from(log: String): Log {
            val (id, content) = log.split(" ", limit = 2)
            return Log(id, LogContents(content))
        }
    }
}

private class LogContents(private val content: String) : Comparable<LogContents> {
    private val contents: List<Content> = content.split(" ").map { Content.from(it) }

    override fun compareTo(other: LogContents): Int {
        return this.content.compareTo(other.content)
    }

    fun containsAllDigitLog(): Boolean {
        return contents.all { it is DigitContent }
    }

    override fun toString(): String {
        return contents.joinToString(separator = " ") { it.toString() }
    }
}

private sealed class Content(val value: String) {
    override fun toString(): String {
        return value
    }

    companion object {
        fun from(value: String): Content {
            return if (value.isDigitLog()) {
                DigitContent(value)
            } else {
                LetterContent(value)
            }
        }
    }
}

private class DigitContent(value: String) : Content(value)

private class LetterContent(value: String) : Content(value)

private fun String.isDigitLog(): Boolean {
    try {
        this.toCharArray().all { it.isDigit() }
        return true
    } catch (e: NumberFormatException) {
        return false
    }
}

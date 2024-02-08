package leetcode

private const val DIGIT = 'Z' - 'A' + 1

fun main() {
    (1..701).forEach { println(convertToTitle(it)) }
}

private fun convertToTitle(columnNumber: Int): String {

    var remainNumber = columnNumber

    val list = mutableListOf<Int>()
    while (remainNumber > 0) {
        val remain = (remainNumber - 1) % DIGIT
        val quotient = (remainNumber - 1) / DIGIT

        list.add(remain)

        remainNumber = quotient
    }

    return list.joinToString("") { it.toExcelChar().toString() }.reversed()
}

private fun Int.toExcelChar(): Char {
    return ('A'.code + this).toChar()
}



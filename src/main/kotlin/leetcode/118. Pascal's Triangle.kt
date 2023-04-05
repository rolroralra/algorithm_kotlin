package leetcode

fun main() {
    println(generate(5))
}

private fun generate(numRows: Int): List<List<Int>> {
    if (numRows <= 1) {
        return listOf(listOf(1))
    }

    val previousResult = generate(numRows - 1)

    val lastRow = previousResult.last()

    val newRow = mutableListOf(1)

    (0 until lastRow.lastIndex)
        .map { lastRow[it] + lastRow[it + 1]  }
        .forEach { newRow.add(it) }
    newRow.add(1)

    val result = previousResult.toMutableList()
    result.add(newRow)


    return result
}

package leetcode

import java.util.LinkedList

@Suppress("UNUSED")
private fun getRow(rowIndex: Int): List<Int> {
    if (rowIndex == 0) {
        return listOf(1)
    }

    val prevRow = getRow(rowIndex - 1)

    val result = LinkedList(
        prevRow.indices
            .drop(1)
            .map { prevRow[it - 1] + prevRow[it] }
    )

    result.addFirst(1)
    result.addLast(1)

    return result
}

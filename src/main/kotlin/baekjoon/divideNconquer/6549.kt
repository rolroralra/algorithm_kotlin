package baekjoon.divideNconquer

import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()

    while (true) {
        val list = br.readLine().split(" ").map { it.toInt() }

        if (list.size == 1 && list[0] == 0) {
            break
        }

        val section = getSectionHavingMaxArea(list.subList(1, list.size))

        println(section.area)
    }
}

private fun getSectionHavingMaxArea(list: List<Int>, startInclude: Int = 0, endInclude: Int = list.lastIndex): Section {
    if (startInclude == endInclude) {
        return Section(list[startInclude], startInclude, endInclude)
    }

    val midIndex = (startInclude + endInclude) / 2

    val leftSection = getSectionHavingMaxArea(list, startInclude, midIndex)
    val rightSection = getSectionHavingMaxArea(list, midIndex + 1, endInclude)

    val candidateSectionList = mutableListOf(leftSection, rightSection, getMidSectionHavingMaxArea(list, startInclude, endInclude, midIndex))

    return candidateSectionList.maxOf { it }
}

private fun getMidSectionHavingMaxArea(list: List<Int>, startInclude: Int = 0, endInclude: Int = list.lastIndex, midIndex: Int = (startInclude + endInclude) / 2): Section {

    var leftIndex = midIndex
    var rightIndex = midIndex

    var currHeight = list[midIndex]
    var maxSection = Section(currHeight, leftIndex, rightIndex)

    while (startInclude < leftIndex && rightIndex < endInclude) {
        currHeight = if (list[leftIndex - 1] >= list[rightIndex + 1])
            min(currHeight, list[--leftIndex])
        else
            min(currHeight, list[++rightIndex])

        maxSection = maxOf(maxSection, Section(currHeight, leftIndex, rightIndex))
    }

    while (startInclude < leftIndex) {
        currHeight = min(currHeight, list[--leftIndex])
        maxSection = maxOf(maxSection, Section(currHeight, leftIndex, rightIndex))
    }

    while (rightIndex < endInclude) {
        currHeight = min(currHeight, list[++rightIndex])
        maxSection = maxOf(maxSection, Section(currHeight, leftIndex, rightIndex))
    }

    return maxSection
}

data class Section(val height: Int, val leftIndex: Int, val rightIndex: Int): Comparable<Section> {
    override fun compareTo(other: Section): Int {
        return Comparator.comparingLong<Section> { it.area }.compare(this, other)
    }

    private val width: Int = rightIndex - leftIndex + 1

    val area: Long = height.toLong() * width
}


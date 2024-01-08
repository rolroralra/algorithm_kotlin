package leetcode

import kotlin.math.max

fun main() {
    check(trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)) == 6)
    check(trap(intArrayOf(4,2,0,3,2,5)) == 9)
}

private fun trap(height: IntArray): Int {
    val maxHeightIndex = height
        .withIndex()
        .maxByOrNull { it.value }
        ?.index ?: 0

    val leftTrapResult = doTrap(height, 0, maxHeightIndex)
    val rightTrapResult = doTrap(height, height.lastIndex, maxHeightIndex)

    return leftTrapResult + rightTrapResult
}

private fun doTrap(height: IntArray, startIndex: Int, endIndex: Int): Int {
    var result = 0
    var maxHeight = 0

    for (currentIndex in startIndex between endIndex) {
        maxHeight = max(maxHeight, height[currentIndex])

        result += maxHeight - height[currentIndex]
    }

    return result
}

private infix fun Int.between(value: Int): IntProgression {
    return if(this <= value) this..value else this downTo value
}

package algorithm

fun main() {
    println(lowerBound(listOf(1,2,3,3,3,4,5,6,7,8), 3)) // 2
    println(upperBound(listOf(1,2,3,3,3,4,5,6,7,8), 3)) // 5
}

/**
 * Returns the index of the first element greater than or equal to the given [target] in the given sorted [list].
 */
private fun lowerBound(list: List<Int>, target: Int, startIndexInclusive: Int = 0, endIndexExclusive: Int = list.size): Int {
    var start = startIndexInclusive
    var end = endIndexExclusive - 1

    var result: Int = endIndexExclusive

    while (start <= end) {
        val mid = (start + end) / 2

        if (list[mid] < target) {
            start = mid + 1
        } else {
            end = mid - 1
            result = mid
        }
    }

    return if (result in (startIndexInclusive until endIndexExclusive)) result else -(result + 1)
}

/**
 * Returns the index of the first element greater than the given [target] in the given sorted [list].
 */
private fun upperBound(list: List<Int>, target: Int, startIndexInclusive: Int = 0, endIndexExclusive: Int = list.size): Int {
    var start = startIndexInclusive
    var end = endIndexExclusive - 1

    var result: Int = endIndexExclusive

    while (start <= end) {
        val mid = (start + end) / 2

        if (list[mid] <= target) {
            start = mid + 1
        } else {
            end = mid - 1
            result = mid
        }
    }

    return if (result in (startIndexInclusive until endIndexExclusive)) result else -(result + 1)
}

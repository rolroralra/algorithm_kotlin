package algorithm.binarysearch

import algorithm.sort.isSorted

/**
 * @param sortedList
 * @param targetValue
 * @param recursive
 * @param comparator
 * @return the index of [targetValue] if found; otherwise `-(insertionPoint + 1)`, matching [lowerBound]/[upperBound]'s convention.
 */
fun <T : Comparable<T>> binarySearch(
    sortedList: List<T>,
    targetValue: T,
    recursive: Boolean = false,
    comparator: Comparator<T> = Comparator.naturalOrder()
): Int {
    check(sortedList.isSorted(comparator))

    if (recursive) {
        return binarySearchByRecursive(sortedList, targetValue, 0, sortedList.lastIndex, comparator)
    }

    return binarySearchByLoop(sortedList, targetValue, comparator)
}

/**
 * @param sortedList
 * @param targetValue
 * @param startIndexInclusive
 * @param endIndexInclusive
 * @param comparator* @return the index of [targetValue] if found; otherwise `-(insertionPoint + 1)`, matching [lowerBound]/[upperBound]'s convention.
 */
fun <T : Comparable<T>> binarySearchByRecursive(
    sortedList: List<T>,
    targetValue: T,
    startIndexInclusive: Int,
    endIndexInclusive: Int,
    comparator: Comparator<T> = Comparator.naturalOrder()
): Int {
    if (startIndexInclusive > endIndexInclusive) {
        return -(startIndexInclusive + 1)
    }

    val midIndex = (startIndexInclusive + endIndexInclusive) / 2

    if (sortedList[midIndex] > targetValue) {
        return binarySearchByRecursive(sortedList, targetValue, startIndexInclusive, midIndex - 1, comparator)
    } else if (sortedList[midIndex] < targetValue) {
        return binarySearchByRecursive(sortedList, targetValue, midIndex + 1, endIndexInclusive, comparator)
    }

    return midIndex
}

/**
 * @param sortedList
 * @param targetValue
 * @param comparator
 * @return the index of [targetValue] if found; otherwise `-(insertionPoint + 1)`, matching [lowerBound]/[upperBound]'s convention.
 */
fun <T : Comparable<T>> binarySearchByLoop(
    sortedList: List<T>,
    targetValue: T,
    comparator: Comparator<T> = Comparator.naturalOrder()
): Int {
    check(sortedList.isSorted(comparator))

    var startIndex = 0
    var endIndex = sortedList.lastIndex

    while (startIndex <= endIndex) {
        val midIndex = (startIndex + endIndex) / 2

        if (sortedList[midIndex] == targetValue) {
            return midIndex
        } else if (sortedList[midIndex] > targetValue) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex + 1
        }
    }

    return -(startIndex + 1)
}


package algorithm.binarysearch

/**
 * @return the index of [target] if found; otherwise `-(insertionPoint + 1)`, matching [lowerBound]/[upperBound]'s convention.
 */
fun <T : Comparable<T>> binarySearch(
    list: List<T>,
    target: T,
    recursive: Boolean = false,
    comparator: Comparator<T> = Comparator.naturalOrder()
): Int {
    TODO("Implement iterative and recursive binary search")
}

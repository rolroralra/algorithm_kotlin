package baekjoon.lis

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val list = br.readLine().split(" ").map { it.toInt() }

    println(getLengthOfLIS(list))
}


private fun getLengthOfLIS(arr: List<Int>): Int {
    val lis = mutableListOf<Int>()

    for (value in arr) {
        var lisIndex = lowerBound(lis, value)

        lisIndex = if (lisIndex < 0) -(lisIndex + 1) else lisIndex

        if (lisIndex in lis.indices) {
            lis[lisIndex] = value
        } else {
            lis.add(value)
        }
    }

    return lis.size
}

private fun <T: Comparable<T>> lowerBound(
    list: List<T>, target: T, startIndexInclusive: Int = 0, endIndexExclusive: Int = list.size,
    comparator: Comparator<T> = Comparator.naturalOrder()): Int {

    var start = startIndexInclusive
    var end = endIndexExclusive - 1

    var result: Int = endIndexExclusive

    while (start <= end) {
        val mid = (start + end) / 2

        if (comparator.compare(list[mid], target) < 0) {
            start = mid + 1
        } else {
            end = mid - 1
            result = mid
        }
    }

    return if (result in (startIndexInclusive until endIndexExclusive)) result else -(result + 1)
}


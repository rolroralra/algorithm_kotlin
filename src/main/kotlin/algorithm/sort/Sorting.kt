package algorithm.sort

import algorithm.heap.Heap
import java.util.Comparator

fun main() {
    val n = 10_000
    val inputList = (1..n).shuffled()

    val timeMap = mutableMapOf<String, Long>()
    var prevTimeStamp = System.currentTimeMillis()

    if (n <= 30_000) {
        SortingAlgorithm.selectionSort(inputList)
        prevTimeStamp = addUsedTime(timeMap, prevTimeStamp, "selection sort")

        SortingAlgorithm.insertionSort(inputList)
        prevTimeStamp = addUsedTime(timeMap, prevTimeStamp, "insertion sort")

        SortingAlgorithm.bubbleSort(inputList)
        prevTimeStamp = addUsedTime(timeMap, prevTimeStamp, "bubble sort")
    }

    SortingAlgorithm.mergeSort(inputList)
    prevTimeStamp = addUsedTime(timeMap, prevTimeStamp, "merge sort")

    SortingAlgorithm.quickSort(inputList)
    prevTimeStamp = addUsedTime(timeMap, prevTimeStamp, "quick sort")

    SortingAlgorithm.heapSort(inputList)
    prevTimeStamp = addUsedTime(timeMap, prevTimeStamp, "heap sort")

    timeMap.forEach { println("${it.key} : ${it.value} ms")}
}

private fun addUsedTime(timeList: MutableMap<String, Long>, prevTimeStamp: Long, algorithm: String): Long {
    val currTimeStamp = System.currentTimeMillis()
    timeList[algorithm] = currTimeStamp - prevTimeStamp

    return currTimeStamp
}

fun <T:Comparable<T>> List<T>.isSorted(): Boolean {
    return this.sorted().withIndex().all { it.value == this[it.index] }
}

object SortingAlgorithm {
    fun <T:Comparable<T>> selectionSort(list: List<T>, startIndexInclude: Int = 0, endIndexExclude: Int = list.size,
                                        comparator: Comparator<T> = Comparator.naturalOrder()): List<T> {
        val result = list.subList(startIndexInclude, endIndexExclude).toMutableList()
        for (i in 0 until list.lastIndex) {
            var minIndex = i
            for (j in (i + 1..result.lastIndex)) {
                if (comparator.compare(result[j], result[minIndex]) < 0) {
                    minIndex = j
                }
            }

            result.swap(i, minIndex)
        }

        checkSortResult(result)

        return result
    }

    fun <T:Comparable<T>> insertionSort(list: List<T>, startIndexInclude: Int = 0, endIndexExclude: Int = list.size,
                                        comparator: Comparator<T> = Comparator.naturalOrder()): List<T> {
        val result = list.subList(startIndexInclude, endIndexExclude).toMutableList()
        for (i in 1..list.lastIndex) {
            for (j in i - 1 downTo 0) {
                if (comparator.compare(result[j], result[j + 1]) <= 0) {
                    break
                }

                result.swap(j, j + 1)
            }
        }

        checkSortResult(result)

        return result
    }

    fun <T:Comparable<T>> bubbleSort(list: List<T>, startIndexInclude: Int = 0, endIndexExclude: Int = list.size,
                                     comparator: Comparator<T> = Comparator.naturalOrder()): List<T> {
        val result = list.subList(startIndexInclude, endIndexExclude).toMutableList()
        for (i in 0 until list.lastIndex) {
            var isSwapped = false
            for (j in 0 until list.lastIndex - i) {
                if (comparator.compare(result[j], result[j + 1]) > 0) {
                    result.swap(j, j + 1)
                    isSwapped = true
                }
            }

            if (isSwapped.not()) {
                break
            }
        }

        checkSortResult(result)

        return result
    }

    fun <T:Comparable<T>> mergeSort(list: List<T>, startIndexInclude: Int = 0, endIndexExclude: Int = list.size,
                                    comparator: Comparator<T> = Comparator.naturalOrder()): List<T> {
        val result = list.toMutableList()

        mergeSort(result, startIndexInclude, endIndexExclude, comparator)

        checkSortResult(result)

        return result
    }

    fun <T:Comparable<T>> quickSort(list: List<T>, startIndexInclude: Int = 0, endIndexExclude: Int = list.size,
                                    comparator: Comparator<T> = Comparator.naturalOrder()): List<T> {
        val result = list.toMutableList()

        quickSort(result, startIndexInclude, endIndexExclude, comparator)

        checkSortResult(result)

        return result
    }

    fun <T:Comparable<T>> heapSort(list: List<T>, startIndexInclude: Int = 0, endIndexExclude: Int = list.size,
                                    comparator: Comparator<T> = Comparator.naturalOrder()): List<T> {
        val result = mutableListOf<T>()

        val heap = Heap(list.subList(startIndexInclude, endIndexExclude), comparator)

        while (heap.isNotEmpty()) {
            result.add(heap.poll())
        }

        checkSortResult(result)

        return result
    }

    private fun <T:Comparable<T>> mergeSort(list: MutableList<T>, startIndexInclude: Int = 0, endIndexExclude: Int = list.size,
                                            comparator: Comparator<T> = Comparator.naturalOrder()) {
        if (startIndexInclude >= endIndexExclude - 1) {
            return
        }

        val midIndex = (startIndexInclude + endIndexExclude) / 2
        mergeSort(list, startIndexInclude, midIndex, comparator)
        mergeSort(list, midIndex, endIndexExclude, comparator)

        var leftIndex = startIndexInclude
        var rightIndex = midIndex
        var resultIndex = 0

        val mergedList = list.subList(startIndexInclude, endIndexExclude).toMutableList()

        while (leftIndex < midIndex && rightIndex < endIndexExclude) {
            if (comparator.compare(list[leftIndex], list[rightIndex]) < 0) {
                mergedList[resultIndex++] = list[leftIndex]
                leftIndex++
            } else {
                mergedList[resultIndex++] = list[rightIndex]
                rightIndex++
            }
        }

        while (leftIndex < midIndex) {
            mergedList[resultIndex++] = list[leftIndex++]
        }

        while (rightIndex < endIndexExclude) {
            mergedList[resultIndex++] = list[rightIndex++]
        }

        (startIndexInclude until endIndexExclude).forEach {
            list[it] = mergedList[it - startIndexInclude]
        }
    }

    private fun <T:Comparable<T>> quickSort(list: MutableList<T>, startIndexInclude: Int = 0, endIndexExclude: Int = list.size,
                                            comparator: Comparator<T> = Comparator.naturalOrder()) {
        if (startIndexInclude >= endIndexExclude - 1) {
            return
        }

        var pivotIndex = startIndexInclude

        for (i in startIndexInclude + 1  until endIndexExclude) {
            if (comparator.compare(list[startIndexInclude], list[i]) > 0) {
                list.swap(++pivotIndex, i)
            }
        }

        list.swap(startIndexInclude, pivotIndex)
        quickSort(list, startIndexInclude, pivotIndex, comparator)
        quickSort(list, pivotIndex + 1, endIndexExclude, comparator)
    }

    private fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        if (index1 == index2) {
            return
        }

        this[index1] = this[index2].also { this[index2] = this[index1] }
    }

    private fun <T:Comparable<T>> checkSortResult(result: List<T>) {
        check(result.isSorted()) { "Failed to sort. This is wrong algorithm." }
    }
}

package baekjoon.heap

open class Heap<T: Comparable<T>>(list: List<T> = emptyList(), comparator: Comparator<T> = Comparator.naturalOrder()) {
    private val dataList: MutableList<T>
    private val comparator: Comparator<T>

    init {
        this.dataList = MutableList(list.size){ list[it] }
        this.comparator = comparator
    }

    open fun isEmpty(): Boolean = dataList.isEmpty()

    open fun add(value: T) {
        dataList.add(value)
        siftUp(dataList.size -1)
    }

    open fun poll(): T {
        val result = peek()

        val lastValue = dataList.removeLast()
        if (dataList.isNotEmpty()) {
            dataList[0] = lastValue
            siftDown(0)
        }

        return result
    }

    open fun peek(): T = dataList.first()

    private fun heapifyTopDown() {
        for (i in 1 until dataList.size) {
            siftUp(i)
        }
    }

    private fun heapifyBottomUp() {
        for (i in (dataList.size - 2) / 2 downTo 0) {
            siftDown(i)
        }
    }

    private fun siftDown(index: Int) {
        var parentIndex = index
        var leftChildIndex = parentIndex * 2 + 1
        var rightChildIndex = leftChildIndex + 1

        while (leftChildIndex < dataList.size) {
            var candidateIndex = parentIndex
            if (comparator.compare(dataList[leftChildIndex], dataList[candidateIndex]) < 0) {
                candidateIndex = leftChildIndex
            }

            if (rightChildIndex < dataList.size && comparator.compare(dataList[rightChildIndex], dataList[candidateIndex]) < 0) {
                candidateIndex = rightChildIndex
            }

            if (candidateIndex == parentIndex) {
                break
            }

            swap(candidateIndex, parentIndex)
            parentIndex = candidateIndex
            leftChildIndex = parentIndex * 2 + 1
            rightChildIndex = leftChildIndex + 1
        }
    }

    private fun siftUp(index: Int) {
        var currIndex = index
        while (currIndex > 0) {
            val parentIndex = (currIndex - 1) / 2
            if (comparator.compare(dataList[currIndex], dataList[parentIndex]) >= 0) {
                break
            }
            swap(currIndex, parentIndex)
            currIndex = parentIndex
        }
    }

    private fun swap(leftIndex: Int, rightIndex: Int) {
        dataList[leftIndex] = dataList[rightIndex].also { dataList[rightIndex] = dataList[leftIndex] }
    }
}

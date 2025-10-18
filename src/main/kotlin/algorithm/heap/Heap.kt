package algorithm.heap

open class Heap<T : Comparable<T>>(
    list: List<T> = emptyList(),
    private val comparator: Comparator<T> = Comparator.naturalOrder(),
    initMode: HeapifyMode = HeapifyMode.BOTTOM_UP,
    private val style: HeapSiftStyle = HeapSiftStyle.LOOP
) {

    private val dataList: MutableList<T> = MutableList(list.size) { list[it] }

    companion object {
        const val ROOT_INDEX = 0
    }

    enum class HeapifyMode {
        TOP_DOWN,
        BOTTOM_UP
    }

    enum class HeapSiftStyle {
        LOOP,
        RECURSIVE
    }

    init {
        when (initMode) {
            HeapifyMode.TOP_DOWN -> heapifyTopDown()
            HeapifyMode.BOTTOM_UP -> heapifyBottomUp()
        }
    }

    open fun isEmpty(): Boolean = dataList.isEmpty()

    open fun isNotEmpty(): Boolean = !isEmpty()

    open fun add(value: T) {
        dataList.add(value)
        siftUp(lastIndex())
    }

    open fun poll(): T {
        val result = peek()

        val lastValue = dataList.removeLast()
        if (dataList.isNotEmpty()) {
            dataList[ROOT_INDEX] = lastValue
            siftDown(ROOT_INDEX)
        }

        return result
    }

    open fun peek(): T = dataList.first()

    open fun clear() {
        dataList.clear()
    }

    open fun print() {
        println(dataList)
    }

    fun heapifyTopDown() {
        for (i in ROOT_INDEX + 1..lastIndex()) {
            siftUp(i)
        }
    }

    fun heapifyBottomUp() {
        for (i in parentIndex(lastIndex()) downTo ROOT_INDEX) {
            siftDown(i)
        }
    }

    fun lastIndex(): Int = dataList.lastIndex

    fun parentIndex(index: Int): Int {
        check(index in (1..dataList.lastIndex))

        return (index - 1) / 2
    }

    fun leftChildIndex(index: Int): Int {
        return 2 * index + 1
    }

    fun rightChildIndex(index: Int): Int {
        return 2 * index + 2
    }

    fun isValidIndex(index: Int): Boolean {
        return index in dataList.indices
    }

    private fun siftDownByLoop(index: Int) {
        var parentIndex = index
        var leftChildIndex = leftChildIndex(parentIndex)
        var rightChildIndex = rightChildIndex(parentIndex)

        while (isValidIndex(leftChildIndex)) {
            var candidateIndex = parentIndex
            if (comparator.compare(dataList[leftChildIndex], dataList[candidateIndex]) < 0) {
                candidateIndex = leftChildIndex
            }

            if (isValidIndex(rightChildIndex) && comparator.compare(
                    dataList[rightChildIndex],
                    dataList[candidateIndex]
                ) < 0
            ) {
                candidateIndex = rightChildIndex
            }

            if (candidateIndex == parentIndex) {
                break
            }

            swap(candidateIndex, parentIndex)
            parentIndex = candidateIndex
            leftChildIndex = leftChildIndex(parentIndex)
            rightChildIndex = rightChildIndex(parentIndex)
        }
    }

    private fun siftDown(index: Int) {
        when (style) {
            HeapSiftStyle.LOOP -> siftDownByLoop(index)
            HeapSiftStyle.RECURSIVE -> siftDownByRecursive(index)
        }
    }

    private fun siftUp(index: Int) {
        when (style) {
            HeapSiftStyle.LOOP -> siftUpByLoop(index)
            HeapSiftStyle.RECURSIVE -> siftUpByRecursive(index)
        }
    }

    private fun siftDownByRecursive(index: Int) {
        var candidateIndex = index
        val leftChildIndex = leftChildIndex(index)
        val rightChildIndex = rightChildIndex(index)

        if (isValidIndex(leftChildIndex) && comparator.compare(
                dataList[leftChildIndex],
                dataList[candidateIndex]
            ) < 0
        ) {
            candidateIndex = leftChildIndex
        }

        if (isValidIndex(rightChildIndex) && comparator.compare(
                dataList[rightChildIndex],
                dataList[candidateIndex]
            ) < 0
        ) {
            candidateIndex = rightChildIndex
        }

        if (candidateIndex != index) {
            swap(candidateIndex, index)
            siftDownByRecursive(candidateIndex)
        }
    }

    private fun siftUpByLoop(index: Int) {
        var currIndex = index
        while (currIndex > ROOT_INDEX) {
            val parentIndex = parentIndex(currIndex)
            if (comparator.compare(dataList[currIndex], dataList[parentIndex]) >= 0) {
                break
            }
            swap(currIndex, parentIndex)
            currIndex = parentIndex
        }
    }

    private fun siftUpByRecursive(index: Int) {
        if (index == ROOT_INDEX) {
            return
        }

        val parentIndex = parentIndex(index)
        if (comparator.compare(dataList[index], dataList[parentIndex]) < 0) {
            swap(index, parentIndex)
            siftUpByRecursive(parentIndex)
        }
    }

    private fun swap(leftIndex: Int, rightIndex: Int) {
        dataList[leftIndex] =
            dataList[rightIndex].also { dataList[rightIndex] = dataList[leftIndex] }
    }
}

fun main() {
    val heap = Heap(
        listOf(2, 4, 5, 1, 3, 6, 9, 8, 7),
        initMode = Heap.HeapifyMode.BOTTOM_UP,
        style = Heap.HeapSiftStyle.RECURSIVE
    )

    heap.print()

    while (heap.isNotEmpty()) {
        println(heap.poll())
    }

    heap.clear()

    heap.add(5)
    heap.add(2)
    heap.add(1)
    heap.add(4)
    heap.add(8)
    heap.add(7)
    heap.add(6)
    heap.add(3)

    heap.print()

    while (heap.isNotEmpty()) {
        println(heap.poll())
    }

    println(getBinaryTreeSize(10))
    println(getSegmentTreeSizeAndBaseIndex(10))
}

fun getBinaryTreeSize(n: Int): Int {
    check(n > 0) { "n must be non-negative" }

    val baseSize = getFullBinarySize(n)

    return 2 * baseSize - 1
}

fun getFullBinarySize(n: Int): Int {
    check(n > 0) { "n must be non-negative" }

    return 1 shl (Int.SIZE_BITS - (n - 1).countLeadingZeroBits() - 1)
}

fun getSegmentTreeSizeAndBaseIndex(n: Int): Pair<Int, Int> {
    check(n > 0) { "n must be non-negative" }

    val baseSize = getFullBinarySize(n)

    return 2 * baseSize - 1 to baseSize - 1
}

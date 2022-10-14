## Template Code
```kotlin
import string.Main

fun main() {
//  System.setIn(Main::class.java.getResourceAsStream("input.txt"))
    val br = System.`in`.bufferedReader(Charsets.UTF_8)
    val t = br.readLine().toInt()

    for (testCase in 1 .. t) {
        val n = br.readLine().toInt()

        val inputs = br.readLine().split(" ").map { it.toInt()}

        val result = solution(inputs[0])
        println("#$testCase $result")
    }
}

fun solution(input: Int): Int {
    // TODO: implementation

    return 0
}
```

## Heap (Priority Queue)
```kotlin
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

    fun heapifyTopDown() {
        for (i in 1 until dataList.size) {
            siftUp(i)
        }
    }

    fun heapifyBottomUp() {
        for (i in (dataList.size - 2) / 2 downTo 0) {
            siftDown(i)
        }
    }

    fun siftDown(index: Int) {
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

    fun siftUp(index: Int) {
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

    fun swap(leftIndex: Int, rightIndex: Int) {
        dataList[leftIndex] = dataList[rightIndex].also { dataList[rightIndex] = dataList[leftIndex] }
    }
}
```

## Cartesian Product
```kotlin
fun main() {
    val list = listOf(listOf(1,2,3), listOf(4,5), listOf(6,7,8))
    println(list.cartesianProduct())
    println(list.cartesianProduct2())
// [[1, 4, 6], [1, 4, 7], [1, 4, 8], [1, 5, 6], [1, 5, 7], [1, 5, 8], [2, 4, 6], [2, 4, 7], [2, 4, 8], [2, 5, 6], [2, 5, 7], [2, 5, 8], [3, 4, 6], [3, 4, 7], [3, 4, 8], [3, 5, 6], [3, 5, 7], [3, 5, 8]] 
// [[1, 4, 6], [2, 4, 6], [3, 4, 6], [1, 5, 6], [2, 5, 6], [3, 5, 6], [1, 4, 7], [2, 4, 7], [3, 4, 7], [1, 5, 7], [2, 5, 7], [3, 5, 7], [1, 4, 8], [2, 4, 8], [3, 4, 8], [1, 5, 8], [2, 5, 8], [3, 5, 8]]    

    println(list.cartesianProduct().containsAll(list.cartesianProduct2()))  // true
}

fun <T> Collection<Iterable<T>>.cartesianProduct(): List<List<T>> =
    if (isEmpty()) emptyList()
    else drop(1)
        .fold(first().map(::listOf)) { acc, iterable ->
            acc.flatMap { list ->
                iterable.map(list::plus)
            }
        }

fun <T> Collection<Iterable<T>>.cartesianProduct2(): List<List<T>> {
    if (isEmpty()) {
        return emptyList()
    }

    var result = mutableListOf(mutableListOf<T>())

    forEach { iterable ->
        result = iterable.flatMap { element ->
            result.map { previous ->
                previous.plus(element).toMutableList()
            }
        }.toMutableList()
    }

    return result
}
```
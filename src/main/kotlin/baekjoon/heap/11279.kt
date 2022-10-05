package baekjoon.heap

fun main() {
    val n = readln().trim().toInt()

    val heap = Heap<Int>(emptyList(), Comparator.naturalOrder<Int>().reversed())

    for (i in 0 until n) {
        when (val input = readln().trim().toInt()) {
            0 -> {
                if (heap.isEmpty()) {
                    println(0)
                } else {
                    println(heap.poll())
                }
            }
            else -> {
                heap.add(input)
            }
        }
    }
}

fun siftDown(index: Int, list: ArrayList<Int>, comparator: Comparator<Int> = Comparator.naturalOrder()) {
    var parentIndex = index
    var leftChildIndex = parentIndex * 2 + 1
    var rightChildIndex = leftChildIndex + 1

    while (leftChildIndex < list.size) {
        var candidateIndex = parentIndex
        if (comparator.compare(list[leftChildIndex], list[candidateIndex]) < 0) {
            candidateIndex = leftChildIndex
        }

        if (rightChildIndex < list.size && comparator.compare(list[rightChildIndex], list[candidateIndex]) < 0) {
            candidateIndex = rightChildIndex
        }

        if (candidateIndex == parentIndex) {
            break
        }

        list.swap(candidateIndex, parentIndex)
        parentIndex = candidateIndex
        leftChildIndex = parentIndex * 2 + 1
        rightChildIndex = leftChildIndex + 1
    }
}

fun siftUp(index: Int, list: MutableList<Int>, comparator: Comparator<Int> = Comparator.naturalOrder()) {
    var currIndex = index
    while (currIndex > 0) {
        val parentIndex = (currIndex - 1) / 2
        if (comparator.compare(list[currIndex], list[parentIndex]) >= 0) {
            break
        }
        list.swap(currIndex, parentIndex)
        currIndex = parentIndex
    }
}

private fun <E> MutableList<E>.swap(leftIndex: Int, rightIndex: Int) {
    if (leftIndex == rightIndex) {
        return
    }

    this[leftIndex] = this[rightIndex].also { this[rightIndex] = this[leftIndex] }
}

package algorithm.heap

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HeapTest {

    @Test
    fun `빈 리스트로 생성한 힙은 비어있다`() {
        val heap = Heap<Int>()

        assertTrue(heap.isEmpty())
        assertEquals(0, heap.size())
    }

    @Test
    fun `add 이후 peek은 최솟값을 반환한다`() {
        val heap = Heap<Int>()
        heap.add(5)
        heap.add(1)
        heap.add(3)

        assertEquals(1, heap.peek())
        assertEquals(3, heap.size())
    }

    @Test
    fun `초기 리스트로부터 힙을 구성한다`() {
        val heap = Heap(listOf(5, 1, 3, 2, 4))

        assertEquals(5, heap.size())
        assertEquals(1, heap.peek())
    }

    @Test
    fun `기본 비교자는 오름차순으로 poll된다`() {
        val values = listOf(3, 1, 4, 1, 5, 9, 2, 6)
        val heap = Heap<Int>()
        values.forEach { heap.add(it) }

        val result = generateSequence { if (heap.isNotEmpty()) heap.poll() else null }.toList()

        assertEquals(values.sorted(), result)
    }

    @Test
    fun `역방향 비교자는 내림차순으로 poll된다`() {
        val values = listOf(3, 1, 4, 1, 5, 9, 2, 6)
        val heap = Heap(comparator = Comparator.reverseOrder<Int>())
        values.forEach { heap.add(it) }

        val result = generateSequence { if (heap.isNotEmpty()) heap.poll() else null }.toList()

        assertEquals(values.sortedDescending(), result)
    }

    @Test
    fun `모두 poll하면 힙이 비워진다`() {
        val heap = Heap(listOf(1, 2, 3))
        while (heap.isNotEmpty()) heap.poll()

        assertTrue(heap.isEmpty())
    }
}

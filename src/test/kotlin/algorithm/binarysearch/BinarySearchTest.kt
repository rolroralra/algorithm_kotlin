package algorithm.binarysearch

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BinarySearchTest {

    @Test
    fun `존재하는 값의 인덱스를 반환한다`() {
        val array = listOf(1, 2, 3, 4, 5)

        array.indices.forEach { index ->
            assertEquals(index, binarySearch(array, array[index]))
        }
    }

    @Test
    fun `원소가 하나인 배열에서도 값을 찾는다`() {
        assertEquals(0, binarySearch(listOf(42), 42))
    }

    @Test
    fun `존재하지 않는 값은 음수 삽입 위치를 반환한다`() {
        val array = listOf(1, 3, 5, 7, 9)

        val result = binarySearch(array, 4)
        val insertionPoint = -(result + 1)

        assertEquals(2, insertionPoint)
    }

    @Test
    fun `모든 원소보다 작은 값을 찾으면 삽입 위치는 0이다`() {
        assertEquals(-1, binarySearch(listOf(1, 2, 3), 0))
    }

    @Test
    fun `모든 원소보다 큰 값을 찾으면 삽입 위치는 배열 끝이다`() {
        assertEquals(-4, binarySearch(listOf(1, 2, 3), 10))
    }

    @Test
    fun `빈 배열에서 탐색하면 삽입 위치는 0이다`() {
        assertEquals(-1, binarySearch(emptyList(), 1))
    }

    @Test
    fun `반복 방식과 재귀 방식의 결과는 같다`() {
        val array = (0..100 step 2).toList()

        assertEquals(binarySearch(array, 50, recursive = false), binarySearch(array, 50, recursive = true))
    }
}

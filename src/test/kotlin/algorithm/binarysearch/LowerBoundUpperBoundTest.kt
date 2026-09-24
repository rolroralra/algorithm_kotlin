package algorithm.binarysearch

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LowerBoundUpperBoundTest {

    private val sortedList = listOf(1, 2, 3, 3, 3, 4, 5, 6, 7, 8)

    @Test
    fun `lowerBound는 target과 일치하는 첫 인덱스를 반환한다`() {
        assertEquals(2, lowerBound(sortedList, 3))
    }

    @Test
    fun `upperBound는 target과 일치하는 마지막 인덱스 다음을 반환한다`() {
        assertEquals(5, upperBound(sortedList, 3))
    }

    @Test
    fun `모든 원소보다 작은 target은 0을 반환한다`() {
        assertEquals(0, lowerBound(sortedList, 0))
        assertEquals(0, upperBound(sortedList, 0))
    }

    @Test
    fun `모든 원소보다 큰 target은 삽입 위치를 인코딩한 음수를 반환한다`() {
        assertEquals(-(sortedList.size + 1), lowerBound(sortedList, 9))
        assertEquals(-(sortedList.size + 1), upperBound(sortedList, 9))
    }

    @Test
    fun `lowerBound와 upperBound 사이의 구간 크기는 target과 일치하는 개수와 같다`() {
        assertEquals(3, upperBound(sortedList, 3) - lowerBound(sortedList, 3))
    }
}

package algorithm.knapsack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KnapsackTest {

    @Test
    fun `0-1 배낭 문제의 고전적인 예제를 푼다`() {
        val result = knapsack(weightList = listOf(2, 3, 4, 5), valueList = listOf(3, 4, 5, 6), boundedWeight = 5)

        assertEquals(7, result)
    }

    @Test
    fun `용량이 0이면 가치도 0이다`() {
        assertEquals(0, knapsack(listOf(1, 2, 3), listOf(10, 20, 30), 0))
    }

    @Test
    fun `용량이 충분하면 모든 아이템을 담는다`() {
        assertEquals(60, knapsack(listOf(1, 2, 3), listOf(10, 20, 30), 6))
    }

    @Test
    fun `중복을 허용하면 최적의 아이템을 반복해서 담는다`() {
        val result = knapsack(listOf(5), listOf(10), 23, isAllowedDuplication = true)

        assertEquals(40, result)
    }

    @Test
    fun `중복을 허용하지 않으면 같은 아이템을 두 번 담지 않는다`() {
        val result = knapsack(listOf(5), listOf(10), 10, isAllowedDuplication = false)

        assertEquals(10, result)
    }
}

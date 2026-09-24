package algorithm.sort

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

class SortingTest {

    companion object {
        @JvmStatic
        fun algorithms(): List<(List<Int>) -> List<Int>> = listOf(
            { list -> SortingAlgorithm.selectionSort(list) },
            { list -> SortingAlgorithm.insertionSort(list) },
            { list -> SortingAlgorithm.bubbleSort(list) },
            { list -> SortingAlgorithm.mergeSort(list) },
            { list -> SortingAlgorithm.quickSort(list) },
            { list -> SortingAlgorithm.heapSort(list) },
        )
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    fun `오름차순으로 정렬한다`(sort: (List<Int>) -> List<Int>) {
        assertEquals(listOf(1, 2, 3, 5, 7, 8, 9), sort(listOf(5, 3, 8, 1, 9, 2, 7)))
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    fun `빈 리스트를 정렬해도 빈 리스트를 반환한다`(sort: (List<Int>) -> List<Int>) {
        assertEquals(emptyList<Int>(), sort(emptyList()))
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    fun `원소가 하나인 리스트는 그대로 반환한다`(sort: (List<Int>) -> List<Int>) {
        assertEquals(listOf(42), sort(listOf(42)))
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    fun `중복된 원소를 포함해도 정렬한다`(sort: (List<Int>) -> List<Int>) {
        assertEquals(listOf(1, 2, 2, 4, 4, 4), sort(listOf(4, 2, 4, 1, 2, 4)))
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    fun `무작위 입력에서도 표준 정렬 결과와 일치한다`(sort: (List<Int>) -> List<Int>) {
        val random = Random(1)
        val array = List(60) { random.nextInt(-50, 50) }

        assertEquals(array.sorted(), sort(array))
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    fun `원본 리스트를 변경하지 않는다`(sort: (List<Int>) -> List<Int>) {
        val original = listOf(3, 1, 2)
        sort(original)

        assertEquals(listOf(3, 1, 2), original)
    }

    @Test
    fun `커스텀 비교자로 내림차순 정렬한다`() {
        val result = SortingAlgorithm.selectionSort(
            listOf(5, 3, 8, 1, 9, 2, 7),
            comparator = Comparator.reverseOrder()
        )

        assertEquals(listOf(9, 8, 7, 5, 3, 2, 1), result)
    }

    @Test
    fun `shellSort는 아직 구현되지 않았다`() {
        assertThrows(NotImplementedError::class.java) {
            SortingAlgorithm.shellSort(listOf(3, 1, 2))
        }
    }
}

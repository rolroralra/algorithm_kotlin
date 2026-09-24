package algorithm.topologicalsort

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TopologicalSortTest {

    companion object {
        @JvmStatic
        fun implementations(): List<(List<List<Int>>) -> Pair<List<Int>, Boolean>> = listOf(
            { adjacentList -> topologicalSortByDfs(adjacentList) },
            { adjacentList -> topologicalSortByIndegree(adjacentList) },
        )
    }

    private fun assertValidOrder(adjacentList: List<List<Int>>, order: List<Int>) {
        val position = order.withIndex().associate { (index, node) -> node to index }
        adjacentList.forEachIndexed { u, neighbors ->
            neighbors.forEach { v -> assertTrue(position.getValue(u) < position.getValue(v)) }
        }
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `모든 정점을 포함한다`(topoSort: (List<List<Int>>) -> Pair<List<Int>, Boolean>) {
        val adjacentList = listOf(listOf(1, 2), listOf(3), listOf(3), emptyList())

        val (order, hasCycle) = topoSort(adjacentList)

        assertFalse(hasCycle)
        assertEquals((0 until adjacentList.size).toList(), order.sorted())
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `간선 순서를 위배하지 않는다`(topoSort: (List<List<Int>>) -> Pair<List<Int>, Boolean>) {
        val adjacentList = listOf(listOf(1, 2), listOf(3), listOf(3), emptyList())

        val (order, _) = topoSort(adjacentList)

        assertValidOrder(adjacentList, order)
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `단일 정점은 그대로 반환한다`(topoSort: (List<List<Int>>) -> Pair<List<Int>, Boolean>) {
        val (order, hasCycle) = topoSort(listOf(emptyList()))

        assertFalse(hasCycle)
        assertEquals(listOf(0), order)
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `단순 순환을 감지한다`(topoSort: (List<List<Int>>) -> Pair<List<Int>, Boolean>) {
        val (_, hasCycle) = topoSort(listOf(listOf(1), listOf(2), listOf(0)))

        assertTrue(hasCycle)
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `자기 자신을 향하는 간선도 순환으로 감지한다`(topoSort: (List<List<Int>>) -> Pair<List<Int>, Boolean>) {
        val (_, hasCycle) = topoSort(listOf(listOf(0)))

        assertTrue(hasCycle)
    }
}

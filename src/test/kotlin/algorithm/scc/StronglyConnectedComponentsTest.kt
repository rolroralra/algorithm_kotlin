package algorithm.scc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class StronglyConnectedComponentsTest {

    companion object {
        @JvmStatic
        fun implementations(): List<(List<List<Int>>) -> List<List<Int>>> = listOf(
            { adjacentList -> sccByTarjan(adjacentList) },
            { adjacentList -> sccByKosaraju(adjacentList) },
        )
    }

    private fun asComponentSet(components: List<List<Int>>): Set<Set<Int>> = components.map { it.toSet() }.toSet()

    @ParameterizedTest
    @MethodSource("implementations")
    fun `간선이 없는 단일 정점은 자기 자신만의 컴포넌트다`(scc: (List<List<Int>>) -> List<List<Int>>) {
        assertEquals(setOf(setOf(0)), asComponentSet(scc(listOf(emptyList()))))
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `두 정점의 순환은 하나의 컴포넌트다`(scc: (List<List<Int>>) -> List<List<Int>>) {
        val adjacentList = listOf(listOf(1), listOf(0))

        assertEquals(setOf(setOf(0, 1)), asComponentSet(scc(adjacentList)))
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `비순환 그래프는 각 정점이 독립된 컴포넌트다`(scc: (List<List<Int>>) -> List<List<Int>>) {
        val adjacentList = listOf(listOf(1), listOf(2), emptyList())

        assertEquals(setOf(setOf(0), setOf(1), setOf(2)), asComponentSet(scc(adjacentList)))
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `모든 정점을 순환하는 그래프는 하나의 컴포넌트다`(scc: (List<List<Int>>) -> List<List<Int>>) {
        val adjacentList = listOf(listOf(1), listOf(2), listOf(3), listOf(0))

        assertEquals(setOf(setOf(0, 1, 2, 3)), asComponentSet(scc(adjacentList)))
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `다리로 연결된 두 순환은 서로 다른 컴포넌트다`(scc: (List<List<Int>>) -> List<List<Int>>) {
        val adjacentList = listOf(listOf(1), listOf(2), listOf(0, 3), listOf(4), listOf(5), listOf(3))

        assertEquals(setOf(setOf(0, 1, 2), setOf(3, 4, 5)), asComponentSet(scc(adjacentList)))
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `자기 자신을 향하는 간선은 독립된 컴포넌트다`(scc: (List<List<Int>>) -> List<List<Int>>) {
        val adjacentList = listOf(listOf(0), listOf(0))

        assertEquals(setOf(setOf(0), setOf(1)), asComponentSet(scc(adjacentList)))
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `모든 정점은 정확히 한 번씩 포함된다`(scc: (List<List<Int>>) -> List<List<Int>>) {
        val adjacentList = listOf(listOf(1), listOf(2), listOf(0, 3), listOf(4), listOf(5, 3), listOf(3))

        val flattened = scc(adjacentList).flatten()

        assertEquals((0 until adjacentList.size).toList(), flattened.sorted())
    }
}

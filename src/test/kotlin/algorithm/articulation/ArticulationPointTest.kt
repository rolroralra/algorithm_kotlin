package algorithm.articulation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArticulationPointTest {

    private fun undirected(edges: List<Pair<Int, Int>>, vertexCount: Int): List<List<Int>> {
        val adjacentList = MutableList(vertexCount) { mutableListOf<Int>() }
        edges.forEach { (a, b) -> adjacentList[a].add(b); adjacentList[b].add(a) }
        return adjacentList
    }

    @Test
    fun `삼각형 그래프에는 단절점이 없다`() {
        val adjacentList = undirected(listOf(0 to 1, 1 to 2, 2 to 0), 3)

        assertEquals(emptyList<Int>(), articulationPoints(adjacentList))
    }

    @Test
    fun `다리의 끝점은 단절점이다`() {
        val adjacentList = undirected(listOf(0 to 1, 1 to 2, 2 to 0, 1 to 3, 3 to 4), 5)

        assertEquals(listOf(1, 3), articulationPoints(adjacentList).sorted())
    }

    @Test
    fun `단순 경로에서는 내부 노드가 모두 단절점이다`() {
        val adjacentList = undirected(listOf(0 to 1, 1 to 2, 2 to 3), 4)

        assertEquals(listOf(1, 2), articulationPoints(adjacentList).sorted())
    }

    @Test
    fun `정점이 둘뿐인 그래프에는 단절점이 없다`() {
        assertEquals(emptyList<Int>(), articulationPoints(undirected(listOf(0 to 1), 2)))
    }

    @Test
    fun `별 모양 그래프에서는 중심만 단절점이다`() {
        val adjacentList = undirected(listOf(0 to 1, 0 to 2, 0 to 3), 4)

        assertEquals(listOf(0), articulationPoints(adjacentList))
    }

    @Test
    fun `연결 요소가 분리되어 있어도 각각 독립적으로 계산한다`() {
        val adjacentList = undirected(listOf(0 to 1, 1 to 2, 3 to 4), 5)

        assertEquals(listOf(1), articulationPoints(adjacentList))
    }
}

package algorithm.articulation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArticulationEdgeTest {

    private fun undirected(edges: List<Pair<Int, Int>>, vertexCount: Int): List<List<Int>> {
        val adjacentList = MutableList(vertexCount) { mutableListOf<Int>() }
        edges.forEach { (a, b) -> adjacentList[a].add(b); adjacentList[b].add(a) }
        return adjacentList
    }

    private fun normalize(edges: List<Pair<Int, Int>>): List<Pair<Int, Int>> =
        edges.map { (a, b) -> if (a < b) a to b else b to a }.sortedWith(compareBy({ it.first }, { it.second }))

    @Test
    fun `삼각형 그래프에는 다리가 없다`() {
        val adjacentList = undirected(listOf(0 to 1, 1 to 2, 2 to 0), 3)

        assertEquals(emptyList<Pair<Int, Int>>(), articulationEdges(adjacentList))
    }

    @Test
    fun `사이클과 매달린 정점을 잇는 간선은 다리다`() {
        val adjacentList = undirected(listOf(0 to 1, 1 to 2, 2 to 0, 1 to 3, 3 to 4), 5)

        assertEquals(listOf(1 to 3, 3 to 4), normalize(articulationEdges(adjacentList)))
    }

    @Test
    fun `단순 경로의 모든 간선은 다리다`() {
        val adjacentList = undirected(listOf(0 to 1, 1 to 2, 2 to 3), 4)

        assertEquals(listOf(0 to 1, 1 to 2, 2 to 3), normalize(articulationEdges(adjacentList)))
    }

    @Test
    fun `별 모양 그래프의 모든 간선은 다리다`() {
        val adjacentList = undirected(listOf(0 to 1, 0 to 2, 0 to 3), 4)

        assertEquals(listOf(0 to 1, 0 to 2, 0 to 3), normalize(articulationEdges(adjacentList)))
    }

    @Test
    fun `간선이 없는 단일 정점에는 다리가 없다`() {
        assertEquals(emptyList<Pair<Int, Int>>(), articulationEdges(listOf(emptyList())))
    }
}

package algorithm.mst

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MinimalSpanningTreeTest {

    private fun sampleAdjacentList(): List<List<Pair<Int, Int>>> = listOf(
        listOf(1 to 2, 2 to 3),
        listOf(0 to 2, 3 to 4),
        listOf(0 to 3, 4 to 1),
        listOf(1 to 4, 4 to 5),
        listOf(2 to 1, 3 to 5)
    )

    @Test
    fun `크루스칼 알고리즘은 최소 총 길이를 계산한다`() {
        val (length, edges) = mstKruskalAlgorithm(sampleAdjacentList())

        assertEquals(10, length)
        assertEquals(4, edges.size)
    }

    @Test
    fun `프림 알고리즘은 최소 총 길이를 계산한다`() {
        val (length, edges) = mstPrimAlgorithm(sampleAdjacentList())

        assertEquals(10, length)
        assertEquals(4, edges.size)
    }

    @Test
    fun `단일 정점 그래프의 최소 신장 트리는 길이가 0이다`() {
        val (length, edges) = mstPrimAlgorithm(listOf(emptyList()))

        assertEquals(0, length)
        assertEquals(0, edges.size)
    }

    @Test
    fun `동일한 그래프에서 두 알고리즘의 결과 길이는 같다`() {
        val (kruskalLength, _) = mstKruskalAlgorithm(sampleAdjacentList())
        val (primLength, _) = mstPrimAlgorithm(sampleAdjacentList())

        assertEquals(kruskalLength, primLength)
    }

    @Test
    fun `dispatcher는 지정한 알고리즘으로 위임한다`() {
        val (kruskalLength, _) = minimalSpanningTree(sampleAdjacentList(), MinimalSpanningTreeAlgorithm.KRUSKAL)
        val (primLength, _) = minimalSpanningTree(sampleAdjacentList(), MinimalSpanningTreeAlgorithm.PRIM)

        assertEquals(10, kruskalLength)
        assertEquals(10, primLength)
    }
}

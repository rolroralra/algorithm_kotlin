package algorithm.lca

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LCATest {

    private fun sampleTree(): LCA {
        // 0 - (1 - (3, 4)), (2 - 5)
        val adjacentList = listOf(listOf(1, 2), listOf(0, 3, 4), listOf(0, 5), listOf(1), listOf(1), listOf(2))
        val lca = LCA(6)
        lca.build(adjacentList, rootIndex = 0)
        return lca
    }

    @Test
    fun `루트의 깊이는 0이다`() {
        assertEquals(0, sampleTree().getDepth(0))
    }

    @Test
    fun `자식의 깊이는 1이다`() {
        val lca = sampleTree()

        assertEquals(1, lca.getDepth(1))
        assertEquals(1, lca.getDepth(2))
    }

    @Test
    fun `형제 노드의 최소 공통 조상은 부모다`() {
        assertEquals(1, sampleTree().lca(3, 4))
    }

    @Test
    fun `사촌 노드의 최소 공통 조상은 루트다`() {
        assertEquals(0, sampleTree().lca(3, 5))
    }

    @Test
    fun `노드와 자기 자신의 최소 공통 조상은 자기 자신이다`() {
        assertEquals(4, sampleTree().lca(4, 4))
    }

    @Test
    fun `인자의 순서는 결과에 영향을 주지 않는다`() {
        val lca = sampleTree()

        assertEquals(lca.lca(3, 5), lca.lca(5, 3))
    }

    @Test
    fun `일직선 트리에서 깊이가 차례로 증가한다`() {
        val adjacentList = listOf(listOf(1), listOf(0, 2), listOf(1, 3), listOf(2, 4), listOf(3))
        val lca = LCA(5)
        lca.build(adjacentList, rootIndex = 0)

        assertEquals(0, lca.lca(4, 0))
        assertEquals(3, lca.lca(3, 4))
        assertEquals(listOf(0, 1, 2, 3, 4), (0 until 5).map { lca.getDepth(it) })
    }
}

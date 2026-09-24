package algorithm.unionfind

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UnionFindTest {

    @Test
    fun `모든 원소는 처음에는 자기 자신이 루트다`() {
        val uf = UnionFind(5)

        (0 until 5).forEach { assertEquals(it, uf.find(it)) }
    }

    @Test
    fun `union은 두 집합을 하나로 합친다`() {
        val uf = UnionFind(5)
        uf.union(0, 1)

        assertEquals(uf.find(0), uf.find(1))
    }

    @Test
    fun `관련 없는 원소는 서로 다른 집합에 남는다`() {
        val uf = UnionFind(5)
        uf.union(0, 1)

        assertNotEquals(uf.find(0), uf.find(2))
    }

    @Test
    fun `union은 전이적으로 적용된다`() {
        val uf = UnionFind(5)
        uf.union(0, 1)
        uf.union(1, 2)

        assertEquals(uf.find(0), uf.find(1))
        assertEquals(uf.find(1), uf.find(2))
    }

    @Test
    fun `이미 연결된 원소를 다시 union해도 결과는 같다`() {
        val uf = UnionFind(3)
        uf.union(0, 1)
        val rootBefore = uf.find(0)

        uf.union(0, 1)

        assertEquals(rootBefore, uf.find(0))
    }

    @Test
    fun `isConnected는 같은 집합 여부를 반환한다`() {
        val uf = UnionFind(3)
        uf.union(0, 1)

        assertTrue(uf.isConnected(0, 1))
        assertFalse(uf.isConnected(0, 2))
    }
}

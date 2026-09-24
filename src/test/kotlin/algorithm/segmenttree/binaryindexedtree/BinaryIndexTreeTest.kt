package algorithm.segmenttree.binaryindexedtree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BinaryIndexTreeTest {

    private fun buildTree(values: List<Int>): BinaryIndexTree {
        val tree = BinaryIndexTree(values.size)
        values.forEachIndexed { index, value -> tree.update(index, value) }
        return tree
    }

    @Test
    fun `전체 구간 합을 구한다`() {
        assertEquals(15, buildTree(listOf(1, 2, 3, 4, 5)).query(0, 4))
    }

    @Test
    fun `부분 구간 합을 구한다`() {
        assertEquals(9, buildTree(listOf(1, 2, 3, 4, 5)).query(1, 3))
    }

    @Test
    fun `단일 인덱스를 조회한다`() {
        assertEquals(3, buildTree(listOf(1, 2, 3, 4, 5)).query(2, 2))
    }

    @Test
    fun `업데이트 이후 조회 결과에 반영된다`() {
        val tree = buildTree(listOf(1, 2, 3, 4, 5))
        tree.update(2, 100)

        assertEquals(1 + 2 + 100 + 4 + 5, tree.query(0, 4))
    }

    @Test
    fun `무작위 값에서도 단순 구간 합과 결과가 일치한다`() {
        val values = List(20) { (it * 7 - 30) % 41 }
        val tree = buildTree(values)

        assertEquals(values.subList(3, 12).sum(), tree.query(3, 11))
    }
}

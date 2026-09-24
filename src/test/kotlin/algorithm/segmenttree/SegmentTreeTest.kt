package algorithm.segmenttree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SegmentTreeTest {

    private fun sumTree(values: List<Int>): SegmentTree<Int> {
        val tree = SegmentTree<Int>(values.size, Int::plus)
        values.forEachIndexed { index, value -> tree.update(index, value) }
        return tree
    }

    @Test
    fun `전체 구간 합을 구한다`() {
        assertEquals(15, sumTree(listOf(1, 2, 3, 4, 5)).query(0, 4))
    }

    @Test
    fun `부분 구간 합을 구한다`() {
        assertEquals(9, sumTree(listOf(1, 2, 3, 4, 5)).query(1, 3))
    }

    @Test
    fun `단일 원소 구간을 조회한다`() {
        assertEquals(3, sumTree(listOf(1, 2, 3, 4, 5)).query(2, 2))
    }

    @Test
    fun `업데이트 이후 조회 결과에 반영된다`() {
        val tree = sumTree(listOf(1, 2, 3, 4, 5))
        tree.update(2, 100)

        assertEquals(1 + 2 + 100 + 4 + 5, tree.query(0, 4))
        assertEquals(100, tree.query(2, 2))
    }

    @Test
    fun `2의 거듭제곱이 아닌 크기도 지원한다`() {
        val tree = sumTree(listOf(1, 2, 3))

        assertEquals(6, tree.query(0, 2))
        assertEquals(3, tree.query(0, 1))
    }

    @Test
    fun `min 연산자로 구간 최솟값을 구한다`() {
        val tree = SegmentTree<Int>(5) { a, b -> minOf(a, b) }
        listOf(5, 3, 8, 1, 9).forEachIndexed { index, value -> tree.update(index, value) }

        assertEquals(1, tree.query(0, 4))
        assertEquals(3, tree.query(0, 1))
    }

    @Test
    fun `max 연산자로 구간 최댓값을 구한다`() {
        val tree = SegmentTree<Int>(5) { a, b -> maxOf(a, b) }
        listOf(5, 3, 8, 1, 9).forEachIndexed { index, value -> tree.update(index, value) }

        assertEquals(9, tree.query(0, 4))
        assertEquals(8, tree.query(0, 2))
    }
}

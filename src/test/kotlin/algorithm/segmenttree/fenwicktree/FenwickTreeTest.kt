package algorithm.segmenttree.fenwicktree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class FenwickTreeTest {

    private fun buildTree(values: List<Int>): FenwickTree {
        val tree = FenwickTree(values.size)
        values.forEachIndexed { index, value -> tree.update(index + 1, value) }
        return tree
    }

    @Test
    fun `전체 구간 합을 구한다`() {
        assertEquals(15, buildTree(listOf(1, 2, 3, 4, 5)).query(1, 5))
    }

    @Test
    fun `부분 구간 합을 구한다`() {
        assertEquals(9, buildTree(listOf(1, 2, 3, 4, 5)).query(2, 4))
    }

    @Test
    fun `단일 인덱스를 조회한다`() {
        assertEquals(3, buildTree(listOf(1, 2, 3, 4, 5)).query(3, 3))
    }

    @Test
    fun `update는 값을 대체하지 않고 차이를 더한다`() {
        val tree = buildTree(listOf(1, 2, 3, 4, 5))
        tree.update(3, 10)

        assertEquals(13, tree.query(3, 3))
        assertEquals(25, tree.query(1, 5))
    }

    @ParameterizedTest
    @MethodSource("providePositiveInts")
    fun `Int#takeLowestBit 는 index & (-index) 와 동일한 값을 가진다`(intValue: Int) {
        assertEquals(intValue.and(-intValue), intValue.takeLowestOneBit())
    }

    companion object {
        @JvmStatic
        fun providePositiveInts(): List<Arguments> {
            return (1..1000).map { Arguments.of(it) }
        }
    }
}

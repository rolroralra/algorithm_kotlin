package algorithm.redblacktree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RedBlackTreeTest {

    private fun assertLlrbInvariants(node: RBNode<Int>?): Int {
        if (node == null) return 1

        assertTrue(node.right == null || node.right?.color == Color.BLACK, "빨간 링크가 오른쪽으로 기울었습니다: ${node.value}")
        if (node.color == Color.RED) {
            assertTrue(node.left == null || node.left?.color == Color.BLACK, "연속된 빨간 링크입니다: ${node.value}")
        }

        val leftBlackHeight = assertLlrbInvariants(node.left)
        val rightBlackHeight = assertLlrbInvariants(node.right)

        assertEquals(leftBlackHeight, rightBlackHeight, "블랙 높이가 다릅니다: ${node.value}")

        return leftBlackHeight + if (node.color == Color.RED) 0 else 1
    }

    @Test
    fun `정렬된 순서로 삽입해도 중위 순회는 정렬 상태를 유지한다`() {
        val rbt = RedBlackTree((1..50).toList(), Comparator.naturalOrder())

        assertEquals((1..50).toList(), rbt.inorder())
    }

    @Test
    fun `루트는 항상 검정색이다`() {
        val rbt = RedBlackTree(listOf(5, 3, 8, 1, 4, 7, 9), Comparator.naturalOrder())

        assertEquals(Color.BLACK, rbt.root?.color)
    }

    @Test
    fun `중복된 값은 무시된다`() {
        val rbt = RedBlackTree(listOf(5, 3, 8, 3, 5), Comparator.naturalOrder())

        assertEquals(3, rbt.size())
    }

    @Test
    fun `정렬된 순서로 삽입해도 LLRB 불변식을 유지한다`() {
        val rbt = RedBlackTree((1..199).toList(), Comparator.naturalOrder())

        assertLlrbInvariants(rbt.root)
    }

    @Test
    fun `리프 노드를 삭제할 수 있다`() {
        val rbt = RedBlackTree(listOf(5, 3, 8), Comparator.naturalOrder())
        rbt.delete(3)

        assertFalse(rbt.contains(3))
        assertEquals(listOf(5, 8), rbt.inorder())
    }

    @Test
    fun `모든 값을 삭제하면 트리는 비어있다`() {
        val values = listOf(5, 3, 8, 1, 4, 7, 9)
        val rbt = RedBlackTree(values, Comparator.naturalOrder())
        values.forEach { rbt.delete(it) }

        assertTrue(rbt.isEmpty())
        assertNull(rbt.root)
    }

    @Test
    fun `최솟값과 최댓값을 조회한다`() {
        val rbt = RedBlackTree(listOf(5, 3, 8, 1, 9), Comparator.naturalOrder())

        assertEquals(1, rbt.findMin())
        assertEquals(9, rbt.findMax())
    }

    @Test
    fun `contains는 값의 존재 여부를 반환한다`() {
        val rbt = RedBlackTree(listOf(5, 3, 8), Comparator.naturalOrder())

        assertTrue(rbt.contains(3))
        assertFalse(rbt.contains(100))
    }

    private data class Person(val name: String, val age: Int)

    @Test
    fun `커스텀 비교자를 사용할 수 있다`() {
        val people = listOf(Person("Bob", 30), Person("Alice", 25), Person("Eve", 35))
        val rbt = RedBlackTree(people, Comparator.comparingInt { it.age })

        assertEquals(listOf(25, 30, 35), rbt.inorder().map { it.age })
    }
}

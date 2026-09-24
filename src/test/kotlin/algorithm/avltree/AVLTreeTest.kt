package algorithm.avltree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AVLTreeTest {

    private fun assertBalanced(node: AVLNode<Int>?): Int {
        if (node == null) return 0

        val leftHeight = assertBalanced(node.left)
        val rightHeight = assertBalanced(node.right)

        assertTrue(kotlin.math.abs(leftHeight - rightHeight) <= 1, "균형이 깨졌습니다: ${node.value}")
        assertEquals(1 + maxOf(leftHeight, rightHeight), node.height)

        return node.height
    }

    @Test
    fun `정렬된 순서로 삽입해도 중위 순회는 정렬 상태를 유지한다`() {
        val avl = AVLTree((1..50).toList(), Comparator.naturalOrder())

        assertEquals((1..50).toList(), avl.inorder())
    }

    @Test
    fun `중복된 값은 무시된다`() {
        val avl = AVLTree(listOf(5, 3, 8, 3, 5), Comparator.naturalOrder())

        assertEquals(3, avl.size())
    }

    @Test
    fun `정렬된 순서로 삽입해도 균형을 유지한다`() {
        val avl = AVLTree((1..199).toList(), Comparator.naturalOrder())

        assertBalanced(avl.root)
    }

    @Test
    fun `리프 노드를 삭제할 수 있다`() {
        val avl = AVLTree(listOf(5, 3, 8), Comparator.naturalOrder())
        avl.delete(3)

        assertFalse(avl.contains(3))
        assertEquals(listOf(5, 8), avl.inorder())
    }

    @Test
    fun `존재하지 않는 값을 삭제해도 아무 일도 일어나지 않는다`() {
        val avl = AVLTree(listOf(5, 3, 8), Comparator.naturalOrder())
        avl.delete(100)

        assertEquals(3, avl.size())
    }

    @Test
    fun `모든 값을 삭제하면 트리는 비어있다`() {
        val values = listOf(5, 3, 8, 1, 4, 7, 9)
        val avl = AVLTree(values, Comparator.naturalOrder())
        values.forEach { avl.delete(it) }

        assertTrue(avl.isEmpty())
        assertNull(avl.root)
    }

    @Test
    fun `최솟값과 최댓값을 조회한다`() {
        val avl = AVLTree(listOf(5, 3, 8, 1, 9), Comparator.naturalOrder())

        assertEquals(1, avl.findMin())
        assertEquals(9, avl.findMax())
    }

    @Test
    fun `빈 트리의 최솟값과 최댓값은 null이다`() {
        val avl = AVLTree<Int>(comparator = Comparator.naturalOrder())

        assertNull(avl.findMin())
        assertNull(avl.findMax())
    }

    @Test
    fun `contains는 값의 존재 여부를 반환한다`() {
        val avl = AVLTree(listOf(5, 3, 8), Comparator.naturalOrder())

        assertTrue(avl.contains(3))
        assertFalse(avl.contains(100))
    }

    private data class Person(val name: String, val age: Int)

    @Test
    fun `커스텀 비교자를 사용할 수 있다`() {
        val people = listOf(Person("Bob", 30), Person("Alice", 25), Person("Eve", 35))
        val avl = AVLTree(people, Comparator.comparingInt { it.age })

        assertEquals(listOf(25, 30, 35), avl.inorder().map { it.age })
        assertEquals(25, avl.findMin()?.age)
        assertEquals(35, avl.findMax()?.age)
    }
}

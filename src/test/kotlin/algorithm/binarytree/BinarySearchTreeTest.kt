package algorithm.binarytree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {

    private fun sampleBst(): BinarySearchTree<Int> {
        val bst = BinarySearchTree<Int>(comparator = Comparator.naturalOrder())
        listOf(5, 3, 8, 1, 4, 7, 9).forEach { bst.addValue(it) }
        return bst
    }

    @Test
    fun `추가한 값을 검색할 수 있다`() {
        val bst = sampleBst()

        assertEquals(7, bst.search(7)?.`val`)
    }

    @Test
    fun `존재하지 않는 값을 검색하면 null을 반환한다`() {
        val bst = sampleBst()

        assertNull(bst.search(100))
    }

    @Test
    fun `중위 순회 결과는 정렬된 순서다`() {
        val bst = sampleBst()

        assertEquals(listOf(1, 3, 4, 5, 7, 8, 9), bst.root!!.inOrderTraversal())
    }

    @Test
    fun `리프 노드를 삭제하면 트리에서 사라진다`() {
        val bst = sampleBst()
        bst.removeValue(1)

        assertNull(bst.search(1))
        assertEquals(listOf(3, 4, 5, 7, 8, 9), bst.root!!.inOrderTraversal())
    }

    @Test
    fun `자식이 둘인 노드를 삭제해도 트리는 균형을 유지한다`() {
        val bst = sampleBst()
        bst.removeValue(5)

        assertNull(bst.search(5))
        assertTrue(bst.isBalanced())
    }

    @Test
    fun `삽입 후에도 트리는 균형을 유지한다`() {
        assertTrue(sampleBst().isBalanced())
    }
}

package algorithm.binarytree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TraversalTest {

    private fun sampleTree() = BinaryTreeNode(
        `val` = 10,
        left = BinaryTreeNode(`val` = 5, left = BinaryTreeNode(`val` = 3), right = BinaryTreeNode(`val` = 7)),
        right = BinaryTreeNode(`val` = 15, left = BinaryTreeNode(`val` = 12), right = BinaryTreeNode(`val` = 20))
    )

    @Test
    fun `전위 순회 결과가 올바르다`() {
        assertEquals(listOf(10, 5, 3, 7, 15, 12, 20), sampleTree().preOrderTraversal())
    }

    @Test
    fun `중위 순회 결과가 올바르다`() {
        assertEquals(listOf(3, 5, 7, 10, 12, 15, 20), sampleTree().inOrderTraversal())
    }

    @Test
    fun `후위 순회 결과가 올바르다`() {
        assertEquals(listOf(3, 7, 5, 12, 20, 15, 10), sampleTree().postOrderTraversal())
    }

    @Test
    fun `레벨 순회 결과가 올바르다`() {
        assertEquals(listOf(10, 5, 15, 3, 7, 12, 20), sampleTree().levelOrderTraversal())
    }

    @Test
    fun `반복(LOOP) 방식과 재귀 방식의 순회 결과는 같다`() {
        val tree = sampleTree()

        assertEquals(tree.preOrderTraversal(Mode.RECURSIVE), tree.preOrderTraversal(Mode.LOOP))
        assertEquals(tree.inOrderTraversal(Mode.RECURSIVE), tree.inOrderTraversal(Mode.LOOP))
        assertEquals(tree.postOrderTraversal(Mode.RECURSIVE), tree.postOrderTraversal(Mode.LOOP))
    }

    @Test
    fun `노드 개수, 리프 개수, 높이를 계산한다`() {
        val tree = sampleTree()

        assertEquals(7, tree.getNodeCount())
        assertEquals(4, tree.getLeafNodeCount())
        assertEquals(3, tree.getHeight())
    }

    @Test
    fun `균형 트리는 isBalanced가 true를 반환한다`() {
        assertTrue(sampleTree().isBalanced())
    }
}

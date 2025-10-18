package algorithm.binarytree

interface BinaryTree {
    fun getNodeCount(): Int

    fun getLeafNodeCount(): Int

    fun getHeight(): Int

    fun isBalanced(): Boolean

    fun printTree()
}
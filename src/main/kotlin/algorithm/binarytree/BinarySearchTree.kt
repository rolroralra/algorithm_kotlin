package algorithm.binarytree

open class BinarySearchTree<T>(var root: BinaryTreeNode<T>? = null, val comparator: Comparator<T>) : BinaryTree {
    open fun search(value: T): BinaryTreeNode<T>? {
        return root?.search(value, comparator)
    }

    open fun addValue(value: T) {
        root = addNode(root, value)
    }

    open fun removeValue(value: T) {
        root = deleteNode(root, value)
    }

    open fun print() {
        root?.printTree()
    }

    private fun addNode(node: BinaryTreeNode<T>?, value: T): BinaryTreeNode<T> {
        if (node == null) {
            return createNode(value)
        }

        val comparison = compare(node, value)

        if (comparison > 0) {
            node.left = addNode(node.left, value)
        } else if (comparison < 0) {
            node.right = addNode(node.right, value)
        }

        // compartison == 0인 경우 (중복): Do Nothing

        return node
    }

    private fun deleteNode(node: BinaryTreeNode<T>?, value: T): BinaryTreeNode<T>? {
        if (node == null) {
            return null
        }

        val comparison = compare(node, value)

        if (comparison > 0) {
            node.left = deleteNode(node.left, value)
            return node
        } else if (comparison < 0) {
            node.right = deleteNode(node.right, value)
            return node
        }

        if (node.left == null) {
            // deallocate memory for node
            return node.right
        } else if (node.right == null) {
            // deallocate memory for node
            return node.left
        }

        val replaceNode = node.right!!.getMinimumNode()
        node.`val` = replaceNode.`val`
        node.right = deleteNode(node.right, replaceNode.`val`)

        return node
    }

    private fun BinaryTreeNode<T>.getMinimumNode(): BinaryTreeNode<T> {
        var node = this

        while (node.left != null) {
            node = node.left!!
        }

        return node
    }

    private fun createNode(value: T): BinaryTreeNode<T> {
        return BinaryTreeNode(value)
    }

    private fun compare(node: BinaryTreeNode<T>, value: T): Int {
        return comparator.compare(node.`val`, value)
    }

    override fun getNodeCount(): Int {
        return root?.getNodeCount() ?: 0
    }

    override fun getLeafNodeCount(): Int {
        return root?.getLeafNodeCount() ?: 0
    }

    override fun getHeight(): Int {
        return root?.getHeight() ?: 0
    }

    override fun isBalanced(): Boolean {
        return root?.isBalanced() ?: true
    }

    override fun printTree() {
        root?.printTree()
    }
}

fun <T> BinaryTreeNode<T>.search(value: T, comparator: Comparator<T>): BinaryTreeNode<T>? {
    return binarySearch(value, this, comparator)
}

fun <T> binarySearch(value: T, node: BinaryTreeNode<T>?, comparator: Comparator<T>): BinaryTreeNode<T>? {
    if (node == null) return null

    val comparison = comparator.compare(node.`val`, value)

    return if (comparison == 0) {
        node
    } else if (comparison > 0) {
        binarySearch(value, node.left, comparator)
    } else {
        binarySearch(value, node.right, comparator)
    }
}

data class Word(val word: String, val meaning: String = "") : Comparable<Word> {
    override fun compareTo(other: Word): Int {
        return word.compareTo(other.word)
    }

    override fun toString(): String {
        return word.take(1)
    }
}

fun main() {
    val bst = BinarySearchTree<Int>(comparator = Comparator.naturalOrder())

    bst.addValue(3)
    bst.addValue(1)
    bst.addValue(5)
    bst.addValue(2)
    bst.addValue(4)
    bst.addValue(6)
    bst.addValue(7)
    bst.addValue(4)
    bst.print()

    val result = bst.search(5)
    result?.printTree() ?: println("There is no node")

    bst.search(100)?.printTree() ?: println("There is no node")
    check(bst.isBalanced())

    bst.removeValue(3)
    bst.printTree()
    check(bst.isBalanced())

    bst.removeValue(5)
    bst.printTree()
    check(bst.isBalanced())

    bst.removeValue(1)
    bst.printTree()
    check(bst.isBalanced())

    bst.removeValue(6)
    bst.printTree()
    check(bst.isBalanced())

    println()
    testBinarySearchTree()
}

private fun testBinarySearchTree() {
    val bst = BinarySearchTree<Word>(comparator = Comparator.naturalOrder())
    bst.addValue(Word("tree", "나무"))
    bst.addValue(Word("student", "학생"))
    bst.addValue(Word("information", "정보"))

    println(bst.search(Word("student"))?.`val` ?: "NONE")

    bst.print()
}
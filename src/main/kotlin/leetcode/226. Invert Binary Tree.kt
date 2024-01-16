package leetcode

import java.util.LinkedList

fun main() {
    val tree = TreeNode(4, TreeNode(2, TreeNode(1), TreeNode(3)), TreeNode(7, TreeNode(6), TreeNode(9)))

    val invertTree = invertTree(tree)

    println(invertTree)
}

private fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) {
        return null
    }

    val invertLeft = invertTree(root.left)
    val invertRight = invertTree(root.right)

    return TreeNode(root.`val`).apply {
        this.left = invertRight
        this.right = invertLeft
    }
}

private fun invertTreeByBfs(root: TreeNode?): TreeNode? {
    if (root == null) {
        return null
    }

    val queue = LinkedList<TreeNode>()
    queue.add(root)

    while(queue.isNotEmpty()) {
        val currNode = queue.poll()

        if (currNode.left != null) {
            queue.add(currNode.left!!)
        }

        if (currNode.right != null) {
            queue.add(currNode.right!!)
        }

        currNode.left = currNode.right.also { currNode.right = currNode.left }
    }

    return root
}

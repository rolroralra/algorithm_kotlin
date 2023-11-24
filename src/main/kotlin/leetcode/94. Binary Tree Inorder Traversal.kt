package leetcode

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    constructor(`val`: Int, left: TreeNode?, right: TreeNode?) : this(`val`) {
        this.left = left
        this.right = right
    }
}

@Suppress("UNUSED")
private fun inorderTraversal(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()

    root?.let { inorderTraversal(it, result) }

    return result
}

private fun inorderTraversal(root: TreeNode, list: MutableList<Int>) {
    root.left?.let { inorderTraversal(it, list) }
    list.add(root.`val`)
    root.right?.let { inorderTraversal(it, list) }
}


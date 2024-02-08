package leetcode


fun main() {
    val treeNode = TreeNode(5)
    treeNode.left = TreeNode(2)
    treeNode.right = TreeNode(3)

    check(hasPathSum(treeNode, 7))
    check(hasPathSum(treeNode, 8))
}
fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) {
        return false
    }

    if (root.isLeaf()) {
        return root.`val` == targetSum
    }

    val nextTargetSum = targetSum - root.`val`
    var result = false
    if (root.left != null) {
        result = result.or(hasPathSum(root.left, nextTargetSum))
    }

    if (result.not() && root.right != null) {
        result = result.or(hasPathSum(root.right, nextTargetSum))
    }

    return result
}

private fun TreeNode.isLeaf(): Boolean {
    return this.left == null && this.right == null
}

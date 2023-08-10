package leetcode

@Suppress("UNUSED")
private fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }

    return isSameTree(symmetric(root.left), root.right)
}

private fun symmetric(root: TreeNode?): TreeNode? {
    if (root == null) {
        return null
    }


    val result = TreeNode(root.`val`)

    result.left= symmetric(root.right)
    result.right = symmetric(root.left)

    return result
}

private fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == q) {
        return true
    }

    if (p == null || q == null) {
        return false
    }

    return p.`val` == q.`val`
            && isSameTree(p.left, q.left)
            && isSameTree(p.right, q.right)
}

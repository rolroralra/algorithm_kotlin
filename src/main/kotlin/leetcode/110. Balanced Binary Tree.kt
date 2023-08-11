package leetcode

import kotlin.math.abs
import kotlin.math.max

@Suppress("UNUSED")
private fun isBalanced(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }

    return isBalanced(root.left)
            && isBalanced(root.right)
            && abs(maxDepth(root.left) - maxDepth(root.right)) <= 1
}

private fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }

    return max(maxDepth(root.left), maxDepth(root.right)) + 1
}

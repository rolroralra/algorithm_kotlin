package leetcode

import kotlin.math.max

@Suppress("UNUSED")
private fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }

    return max(maxDepth(root.left), maxDepth(root.right)) + 1
}

package leetcode

import kotlin.math.min

@Suppress("UNUSED")
private fun minDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }

    if (root.isLeaf()) {
        return 1
    }

    var result = Int.MAX_VALUE

    if (root.left != null) {
        result = min(result, minDepth(root.left) + 1)
    }

    if (root.right != null) {
        result = min(result, minDepth(root.right) + 1)
    }

    return result
}

private fun TreeNode.isLeaf(): Boolean {
    return this.left == null && this.right == null
}

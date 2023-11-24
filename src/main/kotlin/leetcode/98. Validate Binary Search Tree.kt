package leetcode

fun main() {
    println(isValidBST(TreeNode(2, TreeNode(2), TreeNode(2)))) // true
    println(isValidBST(TreeNode(5, TreeNode(1), TreeNode(4, TreeNode(3), TreeNode(6))))) // false
    println(isValidBST(TreeNode(5, TreeNode(4), TreeNode(6, TreeNode(3), TreeNode(7))))) // false
}


private fun isValidBST(root: TreeNode?): Boolean {
    return root.isValid()
}

private fun TreeNode?.isValid(min: Int? = null, max: Int? = null): Boolean {
    if (this == null) {
        return true
    }

    if (min != null && `val` <= min) {
        return false
    }

    if (max != null && `val` >= max) {
        return false
    }

    return left.isValid(min, `val`) && right.isValid(`val`, max)
}



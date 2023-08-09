package leetcode

fun main() {
    check(isSameTree(null, null))
    check(isSameTree(Tree(1), Tree(1)))
    check(isSameTree(Tree(1), Tree(2)).not())
}

private class Tree(var `val`: Int) {
    var left: Tree? = null
    var right: Tree? = null
}

private fun isSameTree(p: Tree?, q: Tree?): Boolean {
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

package algorithm.binarytree

import kotlin.math.max

open class BinaryTreeNode<T>(var`val`: T, var left: BinaryTreeNode<T>? = null, var right: BinaryTreeNode<T>? = null) : BinaryTree {
    enum class PrintMode {
        DIRECTORY,
        TREE
    }
    override fun getNodeCount(): Int {
        return getNodeCount(this)
    }

    override fun getLeafNodeCount(): Int {
        return getLeafNodeCount(this)
    }

    override fun getHeight(): Int {
        return getTreeHeight(this)
    }

    override fun printTree() {
        printTree(PrintMode.TREE)
    }

    open fun printTree(mode: PrintMode = PrintMode.TREE) {
        when (mode) {
            PrintMode.DIRECTORY -> printTreeInternal(this, "", true)
            PrintMode.TREE -> buildTreeLines(this).forEach { println(it) }
        }
    }

    companion object {
        fun <T> getNodeCount(node: BinaryTreeNode<T>?): Int {
            if (node == null) return 0

            return 1 + getNodeCount(node.left) + getNodeCount(node.right)
        }

        fun <T> getLeafNodeCount(node: BinaryTreeNode<T>?): Int {
            if (node == null) return 0

            if (node.left == null && node.right == null) return 1

            return getLeafNodeCount(node.left) + getLeafNodeCount(node.right)
        }

        fun <T> getTreeHeight(node: BinaryTreeNode<T>?): Int {
            if (node == null) return 0

            return 1 + max(getTreeHeight(node.left), getTreeHeight(node.right))
        }

        private fun <T> printTreeInternal(node: BinaryTreeNode<T>?, prefix: String, isTail: Boolean) {
            if (node == null) return

            println(prefix + (if (isTail) "└── " else "├── ") + node.`val`)

            val children = listOfNotNull(node.left, node.right)

            children.forEach { child ->
                val newPrefix = prefix + (if (isTail) "    " else "│   ")

                if (child == node.left && node.right != null) {
                    printTreeInternal(child, newPrefix, false)
                } else {
                    printTreeInternal(child, newPrefix, true)
                }
            }
        }

        private fun <T> buildTreeLines(node: BinaryTreeNode<T>?): List<String> {
            if (node == null) {
                return listOf()
            }

            val value = node.`val`.toString()
            val leftLines = buildTreeLines(node.left)
            val rightLines = buildTreeLines(node.right)

            val leftWidth = leftLines.maxOfOrNull { it.length } ?: 0
            val rightWidth = rightLines.maxOfOrNull { it.length } ?: 0
            val valueWidth = value.length

            val result = mutableListOf<String>()

            // 값의 중심 위치
            val valueCenter = leftWidth + valueWidth / 2

            // 첫 번째 줄: 루트 노드
            val firstLine = StringBuilder()
            firstLine.append(" ".repeat(leftWidth))
            firstLine.append(value)
            firstLine.append(" ".repeat(rightWidth))
            result.add(firstLine.toString())

            // 두 번째 줄: 브랜치 라인
            if (node.left != null || node.right != null) {
                val branchLine = CharArray(leftWidth + valueWidth + rightWidth) { ' ' }

                if (node.left != null) {
                    // 왼쪽 브랜치
                    val leftCenter = (leftWidth - 1) / 2
                    for (i in leftCenter until valueCenter) {
                        if (branchLine[i] == ' ') {
                            branchLine[i] = if (i == leftCenter) '/' else ' '
                        }
                    }
                    branchLine[leftCenter] = '/'
                }

                if (node.right != null) {
                    // 오른쪽 브랜치
                    val rightStart = leftWidth + valueWidth
                    val rightCenter = rightStart + (rightWidth - 1) / 2
                    for (i in (leftWidth + valueWidth - 1)..rightCenter) {
                        if (branchLine[i] == ' ') {
                            branchLine[i] = if (i == rightCenter) '\\' else ' '
                        }
                    }
                    branchLine[rightCenter] = '\\'
                }

                result.add(String(branchLine))
            }

            // 자식 노드 라인들
            val maxChildLines = maxOf(leftLines.size, rightLines.size)
            for (i in 0 until maxChildLines) {
                val line = StringBuilder()

                // 왼쪽 자식
                if (i < leftLines.size) {
                    line.append(leftLines[i])
                } else {
                    line.append(" ".repeat(leftWidth))
                }

                // 중앙 (값 위치)
                line.append(" ".repeat(valueWidth))

                // 오른쪽 자식
                if (i < rightLines.size) {
                    line.append(rightLines[i])
                }

                result.add(line.toString())
            }

            return result
        }
    }
}

fun main() {
    //         10
    //        /  \
    //       5    15
    //      / \   / \
    //     3   7 12  20
    val sampleTree = BinaryTreeNode(
        `val` = 10,
        left = BinaryTreeNode(
            `val` = 5,
            left = BinaryTreeNode(`val` = 3),
            right = BinaryTreeNode(`val` = 7)
        ),
        right = BinaryTreeNode(
            `val` = 15,
            left = BinaryTreeNode(`val` = 12),
            right = BinaryTreeNode(`val` = 20)
        )
    )

    sampleTree.printTree()
}
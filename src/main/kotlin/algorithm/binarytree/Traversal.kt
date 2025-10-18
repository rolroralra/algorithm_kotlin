package algorithm.binarytree

import java.util.Stack

open class TreeNode<T>(var`val`: T, var left: TreeNode<T>? = null, var right: TreeNode<T>? = null) {
    enum class Mode {
        LOOP,
        RECURSIVE,
    }

    open fun preOrderTraversal(mode: Mode = Mode.RECURSIVE): List<T> {
        return when (mode) {
            Mode.RECURSIVE -> preOrderTraversalByRecursive(this)
            Mode.LOOP -> preOrderTraversalByLoop(this)
        }
    }

    open fun postOrderTraversal(mode: Mode = Mode.RECURSIVE): List<T> {
        return when (mode) {
            Mode.RECURSIVE -> postOrderTraversalByRecursive(this)
            Mode.LOOP -> postOrderTraversalByLoop(this)
        }
    }

    open fun inOrderTraversal(mode: Mode = Mode.RECURSIVE): List<T> {
        return when (mode) {
            Mode.RECURSIVE -> inOrderTraversalByRecursive(this)
            Mode.LOOP -> inOrderTraversalByLoop(this)
        }
    }
}

fun <T> preOrderTraversalByRecursive(root: TreeNode<T>?): List<T> {
    val result = mutableListOf<T>()

    preOrderTraversalByRecursiveInternal(root, result)

    return result
}

fun <T> preOrderTraversalByRecursiveInternal(root: TreeNode<T>?, result: MutableList<T>) {
  if (root == null) return

  result.add(root.`val`)
  preOrderTraversalByRecursiveInternal(root.left, result)
  preOrderTraversalByRecursiveInternal(root.right, result)
}

fun <T> inOrderTraversalByRecursive(root: TreeNode<T>?): List<T> {
    val result = mutableListOf<T>()

    inOrderTraversalByRecursiveInternal(root, result)

    return result
}

fun <T> inOrderTraversalByRecursiveInternal(root: TreeNode<T>?, result: MutableList<T>) {
    if (root == null) return

    inOrderTraversalByRecursiveInternal(root.left, result)
    result.add(root.`val`)
    inOrderTraversalByRecursiveInternal(root.right, result)
}

fun <T> postOrderTraversalByRecursive(root: TreeNode<T>?): List<T> {
    val result = mutableListOf<T>()

    postOrderTraversalByRecursiveInternal(root, result)

    return result
}

fun <T> postOrderTraversalByRecursiveInternal(root: TreeNode<T>?, result: MutableList<T>) {
    if (root == null) return

    postOrderTraversalByRecursiveInternal(root.left, result)
    postOrderTraversalByRecursiveInternal(root.right, result)
    result.add(root.`val`)
}

fun <T> preOrderTraversalByLoop(root: TreeNode<T>?): List<T> {
    if (root == null) return emptyList()

    val result = mutableListOf<T>()
    val stack = Stack<TreeNode<T>>()
    stack.push(root)

    while (stack.isNotEmpty()) {
        val node = stack.pop()
        result.add(node.`val`)

        // 오른쪽을 먼저 push (나중에 방문)
        node.right?.let { stack.add(it) }
        node.left?.let { stack.add(it) }
    }

    return result
}

fun <T> inOrderTraversalByLoop(root: TreeNode<T>?): List<T> {
    val result = mutableListOf<T>()
    val stack = Stack<TreeNode<T>>()
    var current = root

    while (current != null || stack.isNotEmpty()) {
        // 왼쪽 끝까지 이동
        while (current != null) {
            stack.push(current)
            current = current.left
        }

        // 스택에서 꺼내서 처리
        current = stack.pop()
        result.add(current.`val`)

        // 오른쪽으로 이동
        current = current.right
    }

    return result
}

fun <T> postOrderTraversalByLoop(root: TreeNode<T>?): List<T> {
    if (root == null) return emptyList()

    val stack1 = Stack<TreeNode<T>>()
    val stack2 = Stack<TreeNode<T>>()
    stack1.push(root)

    while (stack1.isNotEmpty()) {
        val node = stack1.removeLast()
        stack2.push(node)

        // 왼쪽을 먼저 push
        node.left?.let { stack1.push(it) }
        node.right?.let { stack1.push(it) }
    }

    // stack2를 역순으로 pop
    val result = mutableListOf<T>()
    while (stack2.isNotEmpty()) {
        result.add(stack2.pop().`val`)
    }

    return result
}

fun main() {
    //         10
    //        /  \
    //       5    15
    //      / \   / \
    //     3   7 12  20
    val sampleTree = TreeNode(
        `val` = 10,
        left = TreeNode(
            `val` = 5,
            left = TreeNode(`val` = 3),
            right = TreeNode(`val` = 7)
        ),
        right = TreeNode(
            `val` = 15,
            left = TreeNode(`val` = 12),
            right = TreeNode(`val` = 20)
        )
    )

    println("전위 순회: ${sampleTree.preOrderTraversal()}")  // [10, 5, 3, 7, 15, 12, 20]
    println("중위 순회: ${sampleTree.inOrderTraversal()}")   // [3, 5, 7, 10, 12, 15, 20]
    println("후위 순회: ${sampleTree.postOrderTraversal()}")  // [3, 7, 5, 12, 20, 15, 10]

    check(sampleTree.preOrderTraversal() == listOf(10, 5, 3, 7, 15, 12, 20))
    check(sampleTree.inOrderTraversal() == listOf(3, 5, 7, 10, 12, 15, 20))
    check(sampleTree.postOrderTraversal() == listOf(3, 7, 5, 12, 20, 15, 10))
    check(sampleTree.preOrderTraversal(mode = TreeNode.Mode.LOOP) == listOf(10, 5, 3, 7, 15, 12, 20))
    check(sampleTree.inOrderTraversal(mode = TreeNode.Mode.LOOP) == listOf(3, 5, 7, 10, 12, 15, 20))
    check(sampleTree.postOrderTraversal(mode = TreeNode.Mode.LOOP) == listOf(3, 7, 5, 12, 20, 15, 10))
}

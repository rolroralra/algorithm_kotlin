package algorithm.binarytree

import java.util.Stack

enum class Mode {
    LOOP,
    RECURSIVE,
}

fun <T> BinaryTreeNode<T>.preOrderTraversal(mode: Mode = Mode.RECURSIVE): List<T> {
    return when (mode) {
        Mode.RECURSIVE -> preOrderTraversalByRecursive(this)
        Mode.LOOP -> preOrderTraversalByLoop(this)
    }
}

fun <T> BinaryTreeNode<T>.postOrderTraversal(mode: Mode = Mode.RECURSIVE): List<T> {
    return when (mode) {
        Mode.RECURSIVE -> postOrderTraversalByRecursive(this)
        Mode.LOOP -> postOrderTraversalByLoop(this)
    }
}

fun <T> BinaryTreeNode<T>.inOrderTraversal(mode: Mode = Mode.RECURSIVE): List<T> {
    return when (mode) {
        Mode.RECURSIVE -> inOrderTraversalByRecursive(this)
        Mode.LOOP -> inOrderTraversalByLoop(this)
    }
}

fun <T> BinaryTreeNode<T>.levelOrderTraversal(): List<T> {
    val result = mutableListOf<T>()
    val queue = ArrayDeque<BinaryTreeNode<T>>()

    queue.add(this)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        result.add(node.`val`)

        node.left?.let { queue.addLast(it) }
        node.right?.let { queue.addLast(it) }
    }

    return result
}

fun <T> preOrderTraversalByRecursive(root: BinaryTreeNode<T>?): List<T> {
    val result = mutableListOf<T>()

    preOrderTraversalByRecursiveInternal(root, result)

    return result
}

fun <T> preOrderTraversalByRecursiveInternal(root: BinaryTreeNode<T>?, result: MutableList<T>) {
  if (root == null) return

  result.add(root.`val`)
  preOrderTraversalByRecursiveInternal(root.left, result)
  preOrderTraversalByRecursiveInternal(root.right, result)
}

fun <T> preOrderTraversalByLoop(root: BinaryTreeNode<T>?): List<T> {
    if (root == null) return emptyList()

    val result = mutableListOf<T>()
    val stack = Stack<BinaryTreeNode<T>>()
    stack.push(root)

    while (stack.isNotEmpty()) {
        val node = stack.pop()
        result.add(node.`val`)

        // 오른쪽을 먼저 push (나중에 방문)
        node.right?.let { stack.push(it) }
        node.left?.let { stack.push(it) }
    }

    return result
}

fun <T> inOrderTraversalByRecursive(root: BinaryTreeNode<T>?): List<T> {
    val result = mutableListOf<T>()

    inOrderTraversalByRecursiveInternal(root, result)

    return result
}

fun <T> inOrderTraversalByRecursiveInternal(root: BinaryTreeNode<T>?, result: MutableList<T>) {
    if (root == null) return

    inOrderTraversalByRecursiveInternal(root.left, result)
    result.add(root.`val`)
    inOrderTraversalByRecursiveInternal(root.right, result)
}

fun <T> inOrderTraversalByLoop(root: BinaryTreeNode<T>?): List<T> {
    val result = mutableListOf<T>()
    val stack = Stack<BinaryTreeNode<T>>()
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

fun <T> postOrderTraversalByRecursive(root: BinaryTreeNode<T>?): List<T> {
    val result = mutableListOf<T>()

    postOrderTraversalByRecursiveInternal(root, result)

    return result
}

fun <T> postOrderTraversalByRecursiveInternal(root: BinaryTreeNode<T>?, result: MutableList<T>) {
    if (root == null) return

    postOrderTraversalByRecursiveInternal(root.left, result)
    postOrderTraversalByRecursiveInternal(root.right, result)
    result.add(root.`val`)
}

fun <T> postOrderTraversalByLoop(root: BinaryTreeNode<T>?): List<T> {
    if (root == null) return emptyList()

    val stack1 = Stack<BinaryTreeNode<T>>()
    val stack2 = Stack<BinaryTreeNode<T>>()
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

    val expectedPreOrderTraversal = listOf(10, 5, 3, 7, 15, 12, 20)
    val expectedInOrderTraversal = listOf(3, 5, 7, 10, 12, 15, 20)
    val expectedPostOrderTraversal = listOf(3, 7, 5, 12, 20, 15, 10)
    val expectedLevelOrderTraversal = listOf(10, 5, 15, 3, 7, 12, 20)

    val actualPreOrderTraversal = sampleTree.preOrderTraversal()
    val actualInOrderTraversal = sampleTree.inOrderTraversal()
    val actualPostOrderTraversal = sampleTree.postOrderTraversal()
    val actualLevelOrderTraversal = sampleTree.levelOrderTraversal()

    println("전위 순회: $actualPreOrderTraversal")  // [10, 5, 3, 7, 15, 12, 20]
    println("중위 순회: $actualInOrderTraversal")   // [3, 5, 7, 10, 12, 15, 20]
    println("후위 순회: $actualPostOrderTraversal")  // [3, 7, 5, 12, 20, 15, 10]
    println("레벨 순회: $actualLevelOrderTraversal") // [10, 5, 15, 3, 7, 12, 20]

    check(actualPreOrderTraversal == expectedPreOrderTraversal)
    check(actualInOrderTraversal == expectedInOrderTraversal)
    check(actualPostOrderTraversal == expectedPostOrderTraversal)
    check(actualLevelOrderTraversal == expectedLevelOrderTraversal)
    check(sampleTree.preOrderTraversal(mode = Mode.LOOP) == expectedPreOrderTraversal)
    check(sampleTree.inOrderTraversal(mode = Mode.LOOP) == expectedInOrderTraversal)
    check(sampleTree.postOrderTraversal(mode = Mode.LOOP) == expectedPostOrderTraversal)

    println("Tree Node Count: ${sampleTree.getNodeCount()}")
    println("Tree Leaf Node Count: ${sampleTree.getLeafNodeCount()}")
    println("Tree Height: ${sampleTree.getHeight()}")

    check(sampleTree.getNodeCount() == 7)
    check(sampleTree.getLeafNodeCount() == 4)
    check(sampleTree.getHeight() == 3)
}

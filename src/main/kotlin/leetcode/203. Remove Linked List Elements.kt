package leetcode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

private fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    val root = ListNode(0)
    root.next = head

    removeElementsInternal(root, head, `val`)

    return root.next
}

private fun removeElements2(head: ListNode?, `val`: Int): ListNode? {
    val root = ListNode(0)
    root.next = head

    var previousNode = root
    var currentNode = head

    while (currentNode != null) {
        if (currentNode.`val` == `val`) {
            previousNode.next = currentNode.next
        } else {
            previousNode = currentNode
        }

        currentNode = currentNode.next
    }
    return root.next
}

private fun removeElementsInternal(previousNode: ListNode, currentNode: ListNode?, `val`: Int) {
    if (currentNode == null) {
        return
    }

    if (currentNode.`val` == `val`) {
        previousNode.next = currentNode.next
        removeElementsInternal(previousNode, currentNode.next, `val`)
    } else {
        removeElementsInternal(currentNode, currentNode.next, `val`)
    }
}



package leetcode.medium

import java.util.*

fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    var node = head

    val stack = Stack<ListNode>()
    repeat(k) {
        if (node == null) {
            return head
        }

        stack.push(node)
        node = node?.next
    }

    val result = stack.pop()
    var currNode = result
    val nextNode = result.next
    while (stack.isNotEmpty()) {
        currNode.next = stack.pop()
        currNode = currNode.next
    }

    currNode.next = reverseKGroup(nextNode, k)

    return result
}
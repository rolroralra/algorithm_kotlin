package leetcode

import java.util.*

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val queue = LinkedList<ListNode>()
    var node = head

    while (node != null) {
        queue.add(node)

        node = node.next
    }

    var result: ListNode? = head

    if (queue.size == n) {
        result = head?.next
    }

    var prevNode: ListNode? = null
    while (queue.size > n) {
        prevNode = queue.poll()
    }

    prevNode?.next = queue.peek().next

    return result
}

fun removeNthFromEndTwoPointer(head: ListNode?, n: Int): ListNode? {
    var forward: ListNode? = head
    var backward: ListNode? = head
    repeat(n) {
        forward = forward?.next
    }

    if (forward == null) {
        return head?.next
    }

    while (forward?.next != null) {
        forward = forward?.next
        backward = backward?.next
    }

    backward?.next = backward?.next?.next

    return head
}
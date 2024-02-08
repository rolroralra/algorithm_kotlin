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

fun main() {
    val root = ListNode(1)
    val child = ListNode(2)

    root.next = child
    child.next = root

    println(hasCycle2(root))
}

private fun hasCycle(head: ListNode?): Boolean {
    if (head == null) {
        return false
    }

    val visited = mutableMapOf<ListNode, Boolean>()

    var currNode: ListNode = head
    visited[currNode] = true

    while (currNode.next != null) {

        val nextNode: ListNode = currNode.next!!

        if (visited[nextNode] == true) {
            return true
        }

        visited[nextNode] = true

        currNode = nextNode
    }

    return false
}

private fun hasCycle2(head: ListNode?): Boolean {
    var slow: ListNode? = head?.next
    var fast: ListNode? = head?.next?.next
    while (slow != null && fast != null) {
        if (slow == fast) {
            return true
        }
        slow = slow?.next
        fast = fast?.next?.next
    }
    return false
}


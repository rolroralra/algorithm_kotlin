package leetcode

import java.util.*

fun main() {
    val root = ListNode(1)
    root.next = ListNode(2)
    root.next!!.next = ListNode(3)

    val result = reverseList(root)
    println(result!!.`val`)
    println(result.next!!.`val`)
    println(result.next!!.next!!.`val`)

}

private fun reverseList(head: ListNode?): ListNode? {
    var currNode = head
    var prevNode: ListNode? = null
    var nextNode: ListNode?

    while (currNode != null) {
        nextNode = currNode.next
        currNode.next = prevNode

        prevNode = currNode
        currNode = nextNode
    }

    return prevNode
}

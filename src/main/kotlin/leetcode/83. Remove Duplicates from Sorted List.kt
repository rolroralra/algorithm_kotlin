package leetcode

fun main() {
    val head = ListNode(1)
    head.next = ListNode(1)
    head.next?.next = ListNode(2)

    deleteDuplicates(head)

    println(head)
}

fun deleteDuplicates(head: ListNode?): ListNode? {
    if (head == null) {
        return head
    }

    var node = head

    while (isDuplicated(node)) {
        node = node?.next
    }

    head.next = node?.next

    deleteDuplicates(head.next)

    return head
}


fun isDuplicated(node: ListNode?): Boolean {
    if (node == null) {
        return false
    }

    return node.next?.`val` == node.`val`
}

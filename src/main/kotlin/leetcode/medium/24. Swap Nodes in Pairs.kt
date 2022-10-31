package leetcode.medium

fun swapPairs(head: ListNode?): ListNode? {
    if (head?.next == null) {
        return head
    }

    val nextNode = head.next?.next
    val result = head.next
    result?.next = head
    head.next = swapPairs(nextNode)

    return result
}
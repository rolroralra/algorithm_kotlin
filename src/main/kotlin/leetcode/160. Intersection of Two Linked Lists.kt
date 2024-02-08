package leetcode

fun getIntersectionNode(headA:ListNode?, headB:ListNode?): ListNode? {
    return headA.intersect(headB)
}

private fun ListNode?.toList(): List<ListNode> {
    return generateSequence(this) { it.next }.toList()
}

private fun ListNode?.intersect(other: ListNode?): ListNode? {
    return this.toList().intersect(other.toList().toSet()).firstOrNull()
}

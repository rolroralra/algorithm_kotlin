package leetcode

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val headListNode = ListNode(-1)

    var currNode: ListNode? = headListNode
    var carry = 0
    var list1 = l1
    var list2 = l2
    while (list1 != null || list2 != null || carry > 0) {
        val v1 = list1?.`val` ?: 0
        val v2 = list2?.`val` ?: 0

        val sum = v1 + v2 + carry

        carry = sum.div(10)
        currNode?.next = ListNode(sum.rem(10))

        currNode = currNode?.next
        list1 = list1?.next
        list2 = list2?.next
    }

    return headListNode.next
}

//class ListNode(var `val`: Int) {
//    var next: ListNode? = null
//}


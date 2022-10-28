package leetcode.medium

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val result = ListNode(0)

    var left = list1
    var right = list2

    var head = result
    while (left != null && right != null) {
        lateinit var nextNode: ListNode

        if (left.`val` <= right.`val`) {
            nextNode = ListNode(left.`val`)
            left = left.next
        } else {
            nextNode = ListNode(right.`val`)
            right = right.next
        }

        head.next = nextNode
        head = nextNode

    }

    while (left != null) {
        val nextNode = ListNode(left.`val`)
        head.next = nextNode
        head = nextNode
        left = left.next
    }

    while (right != null) {
        val nextNode = ListNode(right.`val`)
        head.next = nextNode
        head = nextNode
        right = right.next
    }

    return result.next
}
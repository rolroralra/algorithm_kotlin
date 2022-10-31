package leetcode

fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    when (lists.size) {
        0 -> return null
        1 -> return lists[0]
        2 -> return mergeTwoListsByCustom(lists[0], lists[1])
    }

    val midSliceIndex = lists.size / 2

    return mergeTwoListsByCustom(
        mergeKLists(lists.sliceArray(0 until midSliceIndex)),
        mergeKLists(lists.sliceArray(midSliceIndex until lists.size))
    )
}

fun mergeTwoListsByCustom(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null) {
        return list2
    }

    if (list2 == null) {
        return list1
    }

    return if (list1.`val` <= list2.`val`) {
        list1.next = mergeTwoListsByCustom(list1.next, list2)
        list1
    } else {
        list2.next = mergeTwoListsByCustom(list1, list2.next)
        list2
    }
}
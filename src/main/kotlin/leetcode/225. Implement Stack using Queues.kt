package leetcode

import java.util.LinkedList

class MyStack() {
    private var queue1 = LinkedList<Int>()
    private var queue2 = LinkedList<Int>()

    fun push(x: Int) {
        queue1.add(x)
    }

    fun pop(): Int {
        while(queue1.size > 1) {
            queue2.add(queue1.pop())
        }

        val result = queue1.pop()

        queue1 = queue2.also { queue2 = queue1 }

        return result
    }

    fun top(): Int {
        while(queue1.size > 1) {
            queue2.add(queue1.pop())
        }

        val result = queue1.pop()
        queue2.add(result)

        queue1 = queue2.also { queue2 = queue1 }

        return result
    }

    fun empty(): Boolean {
        return queue1.isEmpty()
    }
}

package baekjoon.stack

fun main() {
    val n = readln().toInt()

    val stack = arrayOfNulls<Int>(n)

    (1..n).forEach {
        val commands = readln().split(" ")

        val command = commands[0]

        when (command) {
            "push"  -> stack.push(commands[1].toInt())
            "pop" -> println(stack.pop() ?: -1)
            "top" -> println(stack.top() ?: -1)
            "size" -> println(stack.size())
            "empty" -> println(stack.empty())
        }
    }
}

var index = -1

var <T> Array<T>.topIndex: Int
    get() = index
    set(value) { index = value }

fun <T> Array<T>.push(value: T) {
    if (this.topIndex < this.size) {
        this[++this.topIndex] = value
    }
}

fun <T> Array<T>.pop(): T? =
    if (this.topIndex >= 0) {
        this[this.topIndex--]
    } else {
        null
    }

fun <T> Array<T>.top(): T? =
    if (this.topIndex >= 0) {
        this[this.topIndex]
    } else {
        null
    }

fun <T> Array<T>.empty(): Int =
    if (this.topIndex >= 0) {
        0
    } else {
        1
    }

fun <T> Array<T>.size(): Int = this.topIndex + 1

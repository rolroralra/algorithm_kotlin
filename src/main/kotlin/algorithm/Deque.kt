package algorithm

fun main() {
    val deque = ArrayDeque<Int>(listOf(1,2,3,4,5))
    println(deque)
    deque.rotate(-2)
    println(deque)
    deque.rotate(2)
    println(deque)
    deque.rotate(2)
    println(deque)
    deque.rotate(2)
    println(deque)
    deque.rotate(2)
    println(deque)
    deque.rotate(2)
    println(deque)
    deque.rotate(2)
}

fun <T> ArrayDeque<T>.rotate(n: Int) {
    if (this.isEmpty()) {
        return
    }

    val rotationCount = n % this.size

    if (rotationCount < 0) {
        this.rotate(this.size + rotationCount)
        return
    }

    (0 until rotationCount).forEach {
        this.addFirst(this.removeLast())
    }
}
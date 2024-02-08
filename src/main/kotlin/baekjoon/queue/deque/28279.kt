package baekjoon.queue.deque

    import java.util.LinkedList

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val deque = LinkedList<Int>()
    val builder = StringBuilder()
    repeat(n) {
        deque.execute(br.readLine(), builder)
    }

    println(builder.toString())
}

private fun LinkedList<Int>.execute(command: String, builder: StringBuilder) {
    val args = command.split(" ").map { it.toInt() }

    when(args[0]) {
        1 -> addFirst(args[1])
        2 -> addLast(args[1])
        3 -> builder.append(if (isEmpty()) -1 else pollFirst()).append("\n")
        4 -> builder.append(if (isEmpty()) -1 else pollLast()).append("\n")
        5 -> builder.append(size).append("\n")
        6 -> builder.append(if (isEmpty()) 1 else 0).append("\n")
        7 -> builder.append(if (isEmpty()) -1 else peekFirst()).append("\n")
        8 -> builder.append(if (isEmpty()) -1 else peekLast()).append("\n")
    }
}

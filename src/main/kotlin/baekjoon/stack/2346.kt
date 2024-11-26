package baekjoon.stack

data class Balloon(
    val id: Int,
    val move: Int
) {

    fun rotateCount(): Int {
        return if (move > 0) -(move - 1) else -move
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.`out`.bufferedWriter()

    val n = br.readLine().toInt()

    val ballons = br.readLine().splitToSequence(" ").withIndex().map { Balloon(it.index + 1, it.value.toInt())}.toMutableList()

    val sb = StringBuilder()

    while (ballons.isNotEmpty()) {
        val currBalloon = ballons.removeFirst()

        sb.append(currBalloon.id)

        ballons.rotate(currBalloon.rotateCount())

        if (ballons.isNotEmpty()) {
            sb.append(" ")
        }
    }

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}

fun <T> MutableList<T>.rotate(n: Int) {
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

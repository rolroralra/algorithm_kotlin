package leetcode


fun main() {
    println(addToArrayForm(intArrayOf(1, 2, 0, 0), 34))

    println('0'.digitToInt())
}

private fun addToArrayForm(num: IntArray, k: Int): List<Int> {
    val result = num.joinToString("", "", "").toBigInteger().plus(k.toBigInteger())

    return result.toString().chars().map{ it - '0'.toInt() }.toArray().toList()
}

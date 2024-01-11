package leetcode

fun main() {
    hammingWeight("00000000000000000000000000001011".toInt(2)).also { println(it) }
    hammingWeight("00000000000000000000000010000000".toInt(2)).also { println(it) }
    hammingWeight("11111111111111111111111111111101".toUInt(2).toInt()).also { println(it) }

}

// you need treat n as an unsigned value
private fun hammingWeight(n:Int):Int {
    return n.toUInt().countOneBits()
}

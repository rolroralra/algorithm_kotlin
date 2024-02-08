package leetcode

fun main() {
    reverseBits(43261596).also { println(it.toUInt()) }
    reverseBits(4294967293.toUInt().toInt()).also { println(it.toUInt()) }

}
// you need treat n as an unsigned value
private fun reverseBits(n:Int):Int {
//    println(n.toUInt().toString(2).padStart(32, '0'))

    var result = 0
    var input = n

    repeat(Int.SIZE_BITS) {
        result = result shl 1

        if ((input and 1) == 1) {
            result++
        }

        input = input shr 1
    }

//    println((result.toUInt()).toString(2).padStart(32, '0'))

    return result
}

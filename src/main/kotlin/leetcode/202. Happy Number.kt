package leetcode

fun main() {
    19.happiness().also { println(it) }

    println(isHappy2(19))
    println(isHappy2(2))
}

private fun isHappy(n: Int): Boolean {
    val cache = mutableSetOf<Int>()

    return generateSequence(n) {
        cache.add(it)
        it.happiness()
    }.takeWhile {
        cache.contains(it).not()
            .or(cache.contains(1))
    }.contains(1)
}

private fun isHappy2(n: Int): Boolean {
    var fastPointer = n.happiness()

    return generateSequence(n) {
        fastPointer = fastPointer.happiness().happiness()
        it.happiness()
    }.takeWhile {
        it != fastPointer || it == 1
    }.contains(1)
}

private fun Int.happiness(): Int {
    return this.toString().toCharArray()
        .map { it.digitToInt() }
        .sumOf { it * it }
}

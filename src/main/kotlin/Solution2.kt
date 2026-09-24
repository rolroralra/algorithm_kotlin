private fun solution(N: Int): Int {
    return generateSequence(N + 1, { it + 1})
        .first { doesNotContainTwoIdenticalConsecutiveDigits(it) }
}

fun doesNotContainTwoIdenticalConsecutiveDigits(number: Int): Boolean {
    val numberString = "$number"

    return (0 until numberString.lastIndex).none { numberString[it] == numberString[it + 1] }
}

fun main() {
    check(solution(55) == 56)
    check(solution(1765) == 1767)
    check(solution(98) == 101)
    check(solution(44432) == 45010)
    check(solution(3298) == 3401)
}
private fun solution(T: IntArray): Int {
    val valueToIndex = T.withIndex().groupBy({ it.value }, { it.index })
    val remainIndices = T.indices.sorted().toMutableList()

    val minValue = T.min()
    val maxValue = T.max()

    var result = 0
    var cumulativeHours = if (minValue == 1) 0 else (minValue - 1) * remainIndices.size
    var step = 0

    for (value in minValue..maxValue) {
//        println("value = $value")
        val shouldFinishedIndices = valueToIndex[value]

        if (shouldFinishedIndices != null) {
            val reIndices = remainIndices.withIndex().associateBy ({ it.value }, { it.index })
            val intermediateResult = shouldFinishedIndices.sumOf { reIndices.getOrDefault(it, 0) + 1 + cumulativeHours }
//            println("intermediateResult: $intermediateResult")
            result += intermediateResult
        }

        cumulativeHours += remainIndices.size
        remainIndices.removeAll(shouldFinishedIndices ?: emptyList())

        step++
    }

//    println("total step = $step")

    return result
}


fun main() {
    check(solution(intArrayOf(3, 1, 2)) == 13)
    check(solution(intArrayOf(1, 2, 3, 4)) == 24)
    check(solution(intArrayOf(7, 7, 7)) == 60)
    check(solution(intArrayOf(10000)) == 10000)
}
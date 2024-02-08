package leetcode

fun main() {
    println(combinationSum(intArrayOf(2,3,6,7), 7).toString())
    println(combinationSum(intArrayOf(2,3,5), 8).toString())
    println(combinationSum(intArrayOf(2), 1).toString())
}

fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    backtracking(candidates, target, IntArray(candidates.size), 0, result)

    return result
}

private fun backtracking(candidates: IntArray, target: Int, candidateCounts: IntArray, currIndex:Int, result: MutableList<List<Int>>) {
    if (currIndex >= candidates.size) {
        if (target == 0) {
            result.add(
                candidateCounts.withIndex()
                    .flatMap { (index, count) ->
                        (0 until count).map { candidates[index] }
                    }
            )
        }
        return
    }

    val nextIndex = currIndex + 1

    (0 .. target / candidates[currIndex]).forEach { tryCount ->
        candidateCounts[currIndex] = tryCount

        val nextTarget = target - candidates[currIndex] * tryCount

        if (nextTarget >= 0) {
            backtracking(candidates, nextTarget, candidateCounts, nextIndex, result)
        }
    }
}

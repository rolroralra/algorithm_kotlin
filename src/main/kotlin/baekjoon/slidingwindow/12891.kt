package baekjoon.slidingwindow

fun main() {
    val br = System.`in`.bufferedReader()

    val (s, p) = br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }

    val dnaString = br.readLine()

    val dna = "ACGT"

    val countMap =
        br.readLine().split(" ").map { it.toInt() }.withIndex().associateBy({ dna[it.index] }, { it.value })


    var leftPointer = 0
    var rightPointer = p

    val currCountMap = dnaString.substring(leftPointer, rightPointer).dnaCountMap().toMutableMap()
    var result = 0
    while (rightPointer <= s) {
        if (countMap.satisfiedBy(currCountMap)) {
            result++
        }

        if (rightPointer < s) {
            currCountMap[dnaString[leftPointer]]?.minus(1)?.let { currCountMap.put(dnaString[leftPointer], it) }
            (currCountMap[dnaString[rightPointer]] ?: 0).plus(1).let { currCountMap.put(dnaString[rightPointer], it) }
        }

        leftPointer++
        rightPointer++
    }

    print(result)
}

private fun String.dnaCountMap(): Map<Char, Int> {
    return toCharArray().groupBy { it }.mapValues { it.value.size }
}

private fun Map<Char, Int>.satisfiedBy(other: Map<Char, Int>): Boolean {
    return all { it.value <= (other[it.key] ?: 0) }
}

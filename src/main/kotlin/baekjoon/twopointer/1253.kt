package baekjoon.twopointer

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val list = br.readLine().split(" ").map { it.toLong() }.sorted()

    assert(list.size == n)

    println(list.indices.filter { list.hasTwoSumEqualToValueFromIndex(it) }.toSet().size)
}

private fun List<Long>.hasTwoSumEqualToValueFromIndex(index: Int): Boolean {
    var leftPivot = 0
    var rightPivot = lastIndex
    val targetValue = this[index]

    while (leftPivot < rightPivot) {
        val sum = this[leftPivot] + this[rightPivot]
        if (sum == targetValue) {
            if (leftPivot == index) {
                leftPivot++
                continue
            }

            if (rightPivot == index) {
                rightPivot--
                continue
            }

            return true
        } else if (sum < targetValue) {
            leftPivot++
        } else {
            rightPivot--
        }
    }

    return false
}

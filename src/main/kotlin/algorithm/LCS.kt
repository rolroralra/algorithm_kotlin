package algorithm

fun main() {
    println(lcs("ACAYKP", "CAPCAK"))    // ACAK
}

/**
 * Longest Common Subsequence
 */
private fun lcs(input1: String, input2: String): String {
    val dp = List(input1.length) { MutableList(input2.length) { 0 } }

    for (i in input1.indices) {
        for (j in input2.indices) {
            if (input1[i] == input2[j]) {
                dp[i][j] = if (i == 0 || j == 0) 1 else dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = maxOf(
                    if (i == 0) 0 else dp[i - 1][j],
                    if (j == 0) 0 else dp[i][j - 1]
                )
            }
        }
    }

    val lcsLength = dp[input1.length - 1][input2.length - 1]

    val sb = StringBuilder()

    var i = input1.length - 1
    var j = input2.length - 1

    while (sb.length < lcsLength) {
        val (i2, j2, isMatch) = nextMove(i, j, dp)

        if (isMatch) {
            sb.insert(0, input1[i])
        }

        i = i2
        j = j2
    }

    return sb.toString()
}

private fun nextMove(i: Int, j: Int, lcs: List<List<Int>>): Triple<Int, Int, Boolean> {
    if (i > 0 && lcs[i - 1][j] == lcs[i][j]) {
        return Triple(i - 1, j, false)
    }

    if (j > 0 && lcs[i][j - 1] == lcs[i][j]) {
        return Triple(i, j - 1, false)
    }

    return Triple(i - 1, j - 1, true)
}

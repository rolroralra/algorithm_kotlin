package baekjoon.dp

fun main() {
    val br = System.`in`.bufferedReader()

    val (n, k) = br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }

    val arr = (1..n).map { br.readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] } }

    val dp = MutableList(k + 1) { 0 }


    for (i in arr[0].first..k) {
        dp[i] = arr[0].second
    }


    for (i in 1 until n) {
        val weight = arr[i].first
        val value = arr[i].second

        for (j in k downTo 1) {
            if (j - weight >= 0) {
                dp[j] = maxOf(dp[j], dp[j - weight] + value)
            }
        }
    }

    println(dp[k])
}

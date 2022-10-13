package baekjoon.dp

fun main() {
    val br = System.`in`.bufferedReader()
    val t = br.readLine().trim().toInt()

    val obj = object{
        val cache: MutableMap<Int, Long> = mutableMapOf(1 to 1, 2 to 1, 3 to 1, 4 to 2, 5 to 2)

        fun dp(n: Int): Long {
            if (cache.containsKey(n)) {
                return cache[n]!!
            }

            cache[n] = dp(n - 1) + dp(n - 5)

            return cache[n]!!
        }
    }

    for (i in 1..t) {
        val n = br.readLine().trim().toInt()

        println(obj.dp(n))
    }
}



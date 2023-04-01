package leetcode

fun main() {
    println(climbStairs(2))
    println(climbStairs(3))
}

val cache = mutableMapOf(Pair(0 , 1), Pair(1, 1))

fun climbStairs(n: Int): Int {
    if (cache.containsKey(n)) return cache.getValue(n)

    cache[n] = climbStairs(n - 1) + climbStairs(n - 2)
    return cache.getValue(n)
}

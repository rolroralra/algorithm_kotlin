package baekjoon.math

fun main() {
    val br = System.`in`.bufferedReader()

    val t = br.readLine().toInt()

    val primeMap = sieveOfEratosthenes(1_000_000)

    (1..t).forEach { _ ->
        val input = br.readLine().toInt()
        println(getGoldbachPartitionCount(input, primeMap))
    }
}

fun getGoldbachPartitionCount(n: Int, isPrime: Map<Int, Boolean> = sieveOfEratosthenes(n)): Int {
    if (n % 2 != 0) {
        return 0
    }

    var result = 0

    for (i in 2..n / 2) {
        if (isPrime[i] == true && isPrime[n - i] == true) {
//            println("$i ${n - i}")
            result++
        }
    }

    return result
}

fun sieveOfEratosthenes(n: Int): Map<Int, Boolean> {
    val result = (1..n).associateWith { true }.toMutableMap()

    result[1] = false

    for (i in 2..n) {
        if (i * i > n) {
            break
        }

        if (result[i] == false) {
            continue
        }

        for (j in i * i..n step i) {
            result[j] = false
        }
    }

    return result
}

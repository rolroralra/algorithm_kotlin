package leetcode

fun main() {
    println(intToRoman(3))
    println(intToRoman(58))
    println(intToRoman(1994))

}

// Greedy Algorithm
fun intToRoman(num: Int): String {
    val sb = StringBuilder()

    val arr = arrayListOf(1000 to "M", 100 to "C", 10 to "X", 1 to "I")
    val arr2 = arrayListOf(500 to "D", 50 to "L", 5 to "V")

     var input = num

    for (i in arr.indices) {
        if (input <= 0) {
            break
        }

        val quotient = input / arr[i].first
        input %= arr[i].first

        if (quotient < 4) {
            (1..quotient).forEach { _ ->
                sb.append(arr[i].second)
            }
        }
        else if (quotient == 4) {
            sb.append(arr[i].second)
            sb.append(arr2[i - 1].second)
        }
        else if (quotient == 9) {
            sb.append(arr[i].second)
            sb.append(arr[i - 1].second)
        } else {
            sb.append(arr2[i - 1].second)
            (1..quotient - 5).forEach { _ ->
                sb.append(arr[i].second)
            }
        }
    }

    return sb.toString()
}



package leetcode

fun main() {
    println(isPalindrome(121))
    println(isPalindrome(-121))
    println(isPalindrome(10))
}

fun isPalindrome(x: Int): Boolean {
    if (x < 0) {
        return false
    }

    if (x == 0) {
        return true
    }

    return x.toBigInteger() == x.toString().reversed().trimStart('0').toBigInteger()
}
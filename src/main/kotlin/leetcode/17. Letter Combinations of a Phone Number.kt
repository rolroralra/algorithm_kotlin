package leetcode

class `17` {
    private val digitToLetterMap = mapOf('2' to "abc", '3' to "def", '4' to "ghi", '5' to "jkl", '6' to "mno", '7' to "pqrs", '8' to "tuv", '9' to "wxyz")
    private val arr = arrayOf("abc", "def", "ghi", "jkl", "pqrs", "tuv", "wxyz")

//    fun letterCombinations(digits: String): List<String> {
//        val result = mutableListOf<String>()
//
//        val letterCombination = MutableList(digits.length) { '0' }
//
//        bruteForce(0, letterCombination, digits, result)
//
//        return result
//    }

    fun letterCombinations(digits: String): List<String> = digits.mapNotNull { digitToLetterMap.getOrDefault(it, "").toList() }.getCartesianProduct2().map { String(it.toCharArray()) }

    private fun bruteForce(currIndex: Int, letterCombination: MutableList<Char>, digits: String, result: MutableList<String>) {
        if (currIndex >= digits.length) {
            if (digits.isNotEmpty()) {
                result.add(letterCombination.joinToString(""))
            }
            return
        }

        for (letter in digitToLetterMap[digits[currIndex]]!!) {
            letterCombination[currIndex] = letter
            bruteForce(currIndex + 1, letterCombination, digits, result)
        }
    }
}

fun <T> Collection<Iterable<T>>.getCartesianProduct(): List<List<T>> =
    if (isEmpty()) emptyList()
    else drop(1)
        .fold(first().map(::listOf)) { acc, iterable ->
            acc.flatMap { list -> iterable.map(list::plus) }
    }


fun <T> Collection<Iterable<T>>.getCartesianProduct2(): List<List<T>> {
    if (isEmpty()) {
        return emptyList()
    }

    var result = mutableListOf(mutableListOf<T>())

    forEach { iterable ->
        result = iterable.flatMap { element ->
            result.map { previous ->
                previous.plus(element).toMutableList()
            }
        }.toMutableList()
    }

    return result
}

fun main() {
    println(`17`().letterCombinations("23"))
    println(`17`().letterCombinations(""))
    println(`17`().letterCombinations("2"))
}
package leetcode

fun main() {
    println(groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")))
    println(groupAnagrams(arrayOf("a")))

    println(groupAnagrams(arrayOf("")))
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    return strs
        .groupBy(String::anagramsHashCode)
        .values
        .toList()
}

private fun String.anagramsHashCode(): Int {
    return this
        .toCharArray()
        .sorted()
        .joinToString(transform = Char::toString)
        .hashCode()
}

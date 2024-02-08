package leetcode

fun main() {
    println(isAnagram("anagram", "nagaram")) // true
    println(isAnagram("rat", "car")) // false
}

private fun isAnagram(s: String, t: String): Boolean {
    return s.anagramCountMap() == t.anagramCountMap()
}

private fun String.anagramCountMap(): Map<Char, Int> {
    return this
        .toCharArray()
        .groupBy { it }
        .mapValues { it.value.size }
}

private fun String.anagramHashCode(): Int {
    return this
        .toCharArray()
        .sorted()
        .joinToString(transform = Char::toString)
        .hashCode()
}

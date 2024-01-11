package leetcode

fun main() {
    check(isIsomorphic("egg", "add"))
    check(isIsomorphic("foo", "bar").not())
    check(isIsomorphic("paper", "title"))
    check(isIsomorphic("badc", "baba").not())

}

private fun isIsomorphic(s: String, t: String): Boolean {
    if (s.length != t.length) {
        return false
    }

    val isomorphicMap = mutableMapOf<Char, Char>()

    return s.indices.all { index ->
        t[index] == isomorphicMap.getOrPut(s[index]) { t[index] }
    }.and(isomorphicMap.keys.distinct().size == isomorphicMap.values.distinct().size)
}

package leetcode

fun main() {
    println(findSubstring("barfoothefoobarman", arrayOf("foo","bar")))
    println(findSubstring("wordgoodgoodgoodbestword", arrayOf("word","good","best","word")))
    println(findSubstring("barfoofoobarthefoobarman", arrayOf("bar","foo","the")))
}

fun findSubstring(s: String, words: Array<String>): List<Int> {
    val wordCountMap = words.toList().groupingBy { it }.eachCount()
    val wordLength = words.first().length
    val wordCount = words.size
    val totalLength = wordLength * wordCount

    val answer = mutableListOf<Int>()
    for (i in 0 .. s.length - totalLength) {
        val countMap = (i until i + totalLength step wordLength)
            .map { s.slice(it until it + wordLength) }
            .groupingBy { it }.eachCount()

        if (countMap == wordCountMap) {
            answer.add(i)
        }
    }

    return answer
}

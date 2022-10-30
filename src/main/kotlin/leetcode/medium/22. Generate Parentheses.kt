package leetcode.medium


fun main() {
    println(generateParenthesis(3))
}

fun generateParenthesis(n: Int): List<String> {
    val content = MutableList(2 * n) { ' ' }
    val result = mutableListOf<String>()

    backtracking(0, 0, 0, n, content, result)

    return result
}

fun backtracking(
    leftCount: Int,
    rightCount: Int,
    index: Int,
    n: Int,
    content: MutableList<Char>,
    list: MutableList<String>
) {
    if (index == content.size) {
        list.add(content.joinToString(""))
        return
    }

    if (leftCount < n) {
        content[index] = '('
        backtracking(leftCount + 1, rightCount, index + 1, n, content, list)
    }

    if (rightCount < leftCount) {
        content[index] = ')'
        backtracking(leftCount, rightCount + 1, index + 1, n, content, list)
    }
}


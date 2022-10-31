package leetcode

fun main() {
    println(convert("PAYPALISHIRING", 3))
    println(convert("PAYPALISHIRING", 4))
    println(convert("A", 1))

}

fun convert(s: String, numRows: Int): String {
    val sbArray = Array(numRows) { StringBuilder() }
    var index = 0
    while (index < s.length) {
        for (i in 0 until numRows) {
            if (index >= s.length) {
                break
            }

            sbArray[i].append(s[index++])
        }

        for (i in numRows - 2 downTo 1) {
            if (index >= s.length) {
                break
            }

            sbArray[i].append(s[index++])
        }
    }

    return sbArray.fold(StringBuilder()) { result, sb -> result.append(sb) }.toString()
}
fun main() {
    val arr = arrayOf("ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ")
    val map = HashMap<Char, Int>()
    arr.forEachIndexed {
        index, s -> s.forEach {
            map[it] = index + 3
        }
    }

    var result = 0

    readLine()!!.forEach {
        result += map[it]!!
    }

    print(result)
}
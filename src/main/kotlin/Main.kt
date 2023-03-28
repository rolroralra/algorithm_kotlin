fun main() {
//     System.setIn(Main::class.java.getResourceAsStream("input.txt"))
    val br = System.`in`.bufferedReader(Charsets.UTF_8)
    val t = br.readLine().toInt()

    for (testCase in 1 .. t) {
        val inputs = br.readLine().split(" ").map { it.toInt()}

        val result = solution(inputs[0])
        println("#$testCase $result")
    }
}

fun solution(input: Int): Int {
    // TODO: implementation

    return 0
}

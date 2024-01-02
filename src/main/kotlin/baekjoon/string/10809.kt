fun main() {
    val input = readLine()
    if (input != null) {
        solution(input)
    }
}

private fun solution(input: String) {

    val lowerCaseInput = input.lowercase()

    CharRange('a', 'z').forEach{
        print(lowerCaseInput.indexOf(it))
        print(" ")
    }

    println()

}

import java.util.*

fun main() {

    val totalTestCount = readLine()?.toInt()

    for (testCase in 1..totalTestCount!!) {
        val st = StringTokenizer(readLine())
        val r = st.nextToken().toInt()
        st.nextToken().forEach {
            for (i in 1..r) {
                print(it)
            }
        }
        println()
    }
}
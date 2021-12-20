import java.util.*

fun main() {
    val st = StringTokenizer(readLine())

    val a = st.nextToken().reversed().toInt()
    val b = st.nextToken().reversed().toInt()

    println(maxOf(a, b))
}
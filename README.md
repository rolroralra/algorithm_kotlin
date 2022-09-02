### Template Code
```kotlin
import java.util.*
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    // System.setIn(Main::class.java.getResourceAsStream("input.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(readLine())
    
    val T = st.nextToken().toInt()
    
    for (testCase in 1..T) {
        val N = st.nextToken().toInt()

        val result = solution(N)
        println("#$testCase $result")
    }
}

fun solution(input: Int): Int {
    // TODO: implementation
    
    return 0
}

```

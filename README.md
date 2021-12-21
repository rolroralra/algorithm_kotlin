### Template Code
```kotlin
import java.util.*

fun main() {
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
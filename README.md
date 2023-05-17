## Template Code
```kotlin
import string.Main

fun main() {
//  System.setIn(Main::class.java.getResourceAsStream("input.txt"))
    val br = System.`in`.bufferedReader(Charsets.UTF_8)
    val t = br.readLine().toInt()

    for (testCase in 1 .. t) {
        val n = br.readLine().toInt()

        val inputs = br.readLine().split(" ").map { it.toInt()}

        val result = solution(inputs[0])
        println("#$testCase $result")
    }
}

fun solution(input: Int): Int {
    // TODO: implementation

    return 0
}
```
## Lower Bound, Upper Bound
[code example](./src/main/kotlin/algorithm/lowerBoundUpperBound.kt)

## Heap (Priority Queue)
[code example](./src/main/kotlin/algorithm/Heap.kt)

## Cartesian Product
[code example](./src/main/kotlin/algorithm/cartesianProduct.kt)
<details>
    <summary>Code Example</summary>

```kotlin
fun <T> Collection<Iterable<T>>.cartesianProduct(): List<List<T>> =
    if (isEmpty()) emptyList()
    else drop(1)
        .fold(first().map(::listOf)) { acc, iterable ->
            acc.flatMap { list ->
                iterable.map(list::plus)
            }
        }

fun <T> Collection<Iterable<T>>.cartesianProduct2(): List<List<T>> {
    if (isEmpty()) {
        return emptyList()
    }

    var result = mutableListOf(mutableListOf<T>())

    forEach { iterable ->
        result = iterable.flatMap { element ->
            result.map { previous ->
                previous.plus(element).toMutableList()
            }
        }.toMutableList()
    }

    return result
}
```
</details>


## LCS (Longest Common Subsequence)
[code example](./src/main/kotlin/algorithm/dp/LCS.kt)


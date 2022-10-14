package collection

fun main() {
    val list = listOf(listOf(1,2,3), listOf(4,5), listOf(6,7,8))
    println(list.cartesianProduct())
    println(list.cartesianProduct2())

    println(list.cartesianProduct().containsAll(list.cartesianProduct2()))
}

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


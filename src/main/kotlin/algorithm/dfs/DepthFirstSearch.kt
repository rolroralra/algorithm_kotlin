package algorithm.dfs

import java.util.Stack

enum class DfsMode {
    RECURSIVE,
    STACK,
    STACK_NOT_EFFECTIVELY
}

inline fun <reified T> dfs(
    graph: List<List<T>>,
    startIndex: Int = 0,
    noinline visitProcess: (Int) -> Unit = {},
    noinline finishProcess: (Int) -> Unit = {},
    mode: DfsMode = DfsMode.STACK,
) {
    when (T::class) {
        Int::class -> {
            require(graph.all { row ->
                row.all { it is Int }
            })

            @Suppress("UNCHECKED_CAST")
            dfsWithAdjacentMatrix(graph as List<List<Int>>, startIndex, visitProcess, finishProcess, mode)
        }
        Pair::class -> {
            require(graph.all { row ->
                row.all { it is Pair<*, *> }
            })

            @Suppress("UNCHECKED_CAST")
            dfsWithAdjacentList(graph as List<List<Pair<Int, Int>>>, startIndex, visitProcess, finishProcess, mode)
        }
        else -> {
            throw IllegalArgumentException("Unsupported type: ${T::class}")
        }
    }
}

fun dfsWithAdjacentList(
    adjacentList: List<List<Pair<Int, Int>>>,
    startIndex: Int = 0,
    visitProcess: (Int) -> Unit = {},
    finishProcess: (Int) -> Unit = {},
    mode: DfsMode = DfsMode.STACK
) {
    require(adjacentList.isNotEmpty()) { "At least one adjacent is empty" }

    when (mode) {
        DfsMode.RECURSIVE -> dfsInternalByRecursiveWithAdjacentList(
            adjacentList,
            startIndex,
            visitProcess,
            finishProcess
        )

        DfsMode.STACK -> dfsInternalByStackWithAdjacentList(
            adjacentList,
            startIndex,
            visitProcess
        )

        DfsMode.STACK_NOT_EFFECTIVELY -> dfsInternalByStackNotEffectivelyWithAdjacentList(
            adjacentList,
            startIndex,
            visitProcess
        )
    }
}

fun dfsWithAdjacentMatrix(
    adjacentMatrix: List<List<Int>>,
    startIndex: Int = 0,
    visitProcess: (Int) -> Unit = {},
    finishProcess: (Int) -> Unit = {},
    mode: DfsMode = DfsMode.STACK
) {
    require(adjacentMatrix.isNotEmpty() &&
            adjacentMatrix.all { it.size == adjacentMatrix.size }) {
        "adjacent matrix must be non-empty square matrix"
    }

    when (mode) {
        DfsMode.RECURSIVE -> dfsInternalByRecursiveWithAdjacentMatrix(
            adjacentMatrix,
            startIndex,
            visitProcess,
            finishProcess
        )

        DfsMode.STACK -> dfsInternalByStackWithAdjacentMatrix(
            adjacentMatrix,
            startIndex,
            visitProcess
        )

        DfsMode.STACK_NOT_EFFECTIVELY -> dfsInternalByStackNotEffectivelyWithAdjacentMatrix(
            adjacentMatrix,
            startIndex,
            visitProcess
        )
    }
}

private fun dfsInternalByStackWithAdjacentList(
    adjacentList: List<List<Pair<Int, Int>>>,
    startIndex: Int,
    visitProcess: (Int) -> Unit = {}
) {
    val isVisited = MutableList(adjacentList.size) { false }

    val stack = Stack<Int>()
    stack.push(startIndex)
    isVisited[startIndex] = true

    while (stack.isNotEmpty()) {
        val currIndex = stack.pop()

        visitProcess(currIndex)

        @Suppress("UNUSED_VARIABLE")
        for ((nextIndex, edgeLength) in adjacentList[currIndex].reversed()) {
            if (isVisited[nextIndex]) {
                continue
            }

            stack.push(nextIndex)
            isVisited[nextIndex] = true
        }
    }
}

private fun dfsInternalByStackNotEffectivelyWithAdjacentList(
    adjacentList: List<List<Pair<Int, Int>>>,
    startIndex: Int,
    visitProcess: (Int) -> Unit = {}
) {
    val isVisited = MutableList(adjacentList.size) { false }

    val stack = Stack<Int>()
    stack.push(startIndex)

    while (stack.isNotEmpty()) {
        val currIndex = stack.pop()

        if (isVisited[currIndex]) {
            continue
        }

        isVisited[currIndex] = true
        visitProcess(currIndex)

        @Suppress("UNUSED_VARIABLE")
        for ((nextIndex, edgeLength) in adjacentList[currIndex].reversed()) {
            if (isVisited[nextIndex]) {
                continue
            }

            stack.push(nextIndex)
        }
    }
}

private fun dfsInternalByRecursiveWithAdjacentList(
    adjacentList: List<List<Pair<Int, Int>>>,
    currIndex: Int,
    visitProcess: (Int) -> Unit = {},
    finishProcess: (Int) -> Unit = {},
    isVisited: MutableList<Boolean> = MutableList(adjacentList.size) { false },
    isFinished: MutableList<Boolean> = MutableList(adjacentList.size) { false },
) {
    if (isVisited[currIndex]) {
        return
    }

    isVisited[currIndex] = true
    visitProcess(currIndex)

    @Suppress("UNUSED_VARIABLE")
    for ((nextIndex, edgeLength) in adjacentList[currIndex]) {
        if (isVisited[nextIndex]) {
            continue
        }

        dfsInternalByRecursiveWithAdjacentList(
            adjacentList, nextIndex, visitProcess, finishProcess, isVisited, isFinished
        )
    }

    isFinished[currIndex] = true
    finishProcess(currIndex)
}

private fun dfsInternalByStackWithAdjacentMatrix(
    adjacentMatrix: List<List<Int>>,
    startIndex: Int,
    visitProcess: (Int) -> Unit = {}
) {
    val isVisited = MutableList(adjacentMatrix.size) { false }

    val stack = Stack<Int>()
    stack.push(startIndex)
    isVisited[startIndex] = true

    while (stack.isNotEmpty()) {
        val currIndex = stack.pop()

        visitProcess(currIndex)

        for (nextIndex in adjacentMatrix[currIndex].indices.reversed()) {
            if (adjacentMatrix[currIndex][nextIndex] == 0 || isVisited[nextIndex]) {
                continue
            }

            stack.push(nextIndex)
            isVisited[nextIndex] = true
        }
    }
}

private fun dfsInternalByStackNotEffectivelyWithAdjacentMatrix(
    adjacentMatrix: List<List<Int>>,
    startIndex: Int,
    visitProcess: (Int) -> Unit = {}
) {
    val isVisited = MutableList(adjacentMatrix.size) { false }

    val stack = Stack<Int>()
    stack.push(startIndex)

    while (stack.isNotEmpty()) {
        val currIndex = stack.pop()

        if (isVisited[currIndex]) {
            continue
        }

        isVisited[currIndex] = true
        visitProcess(currIndex)

        for (nextIndex in adjacentMatrix[currIndex].indices.reversed()) {
            if (adjacentMatrix[currIndex][nextIndex] == 0 || isVisited[nextIndex]) {
                continue
            }

            stack.push(nextIndex)
        }
    }
}


private fun dfsInternalByRecursiveWithAdjacentMatrix(
    adjacentMatrix: List<List<Int>>,
    currIndex: Int,
    visitProcess: (Int) -> Unit = {},
    finishProcess: (Int) -> Unit = {},
    isVisited: MutableList<Boolean> = MutableList(adjacentMatrix.size) { false },
    isFinished: MutableList<Boolean> = MutableList(adjacentMatrix.size) { false },
) {
    if (isVisited[currIndex]) {
        return
    }

    isVisited[currIndex] = true
    visitProcess(currIndex)

    for (nextIndex in adjacentMatrix[currIndex].indices) {
        if (adjacentMatrix[currIndex][nextIndex] == 0 || isVisited[nextIndex]) {
            continue
        }

        dfsInternalByRecursiveWithAdjacentMatrix(
            adjacentMatrix, nextIndex, visitProcess, finishProcess, isVisited, isFinished
        )
    }

    isFinished[currIndex] = true
    finishProcess(currIndex)
}


fun main() {
    val adjMatrix = listOf(
        listOf(0, 1, 1, 0, 0),
        listOf(1, 0, 0, 1, 0),
        listOf(1, 0, 0, 0, 1),
        listOf(0, 1, 0, 0, 0),
        listOf(0, 0, 1, 0, 0)
    )

    dfs(adjMatrix, 0, { println("$it visited") }, { println("$it finished") }, mode = DfsMode.STACK_NOT_EFFECTIVELY)
    println()
    dfs(adjMatrix, 0, { println("$it visited") }, { println("$it finished") }, mode = DfsMode.STACK)
    println()
    dfs(adjMatrix, 0, { println("$it visited") }, { println("$it finished") }, mode = DfsMode.RECURSIVE)
    println()

    val adjList = listOf(
        listOf(1 to 1, 2 to 1),
        listOf(0 to 1, 3 to 1),
        listOf(0 to 1, 4 to 1),
        listOf(1 to 1),
        listOf(2 to 1)
    )

    dfs(adjList, 0, { println("$it visited") }, { println("$it finished") }, mode = DfsMode.STACK)
    println()
    dfs(adjList, 0, { println("$it visited") }, { println("$it finished") }, mode = DfsMode.STACK_NOT_EFFECTIVELY)
    println()
    dfs(adjList, 0, { println("$it visited") }, { println("$it finished") }, mode = DfsMode.RECURSIVE)
    println()
}
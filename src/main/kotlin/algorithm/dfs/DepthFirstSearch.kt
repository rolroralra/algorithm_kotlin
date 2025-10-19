package algorithm.dfs

import java.util.Stack
import java.util.function.Consumer

class DepthFirstSearch {

    enum class DfsMode {
        RECURSIVE,
        STACK,
        STACK_NOT_EFFECTIVELY
    }

    companion object {
        fun dfs(
            adjacentMatrix: List<List<Int>>,
            startIndex: Int = 0,
            visitProcess: Consumer<Int> = Consumer { },
            finishProcess: Consumer<Int> = Consumer { },
            mode: DfsMode = DfsMode.STACK
        ) {
            check(adjacentMatrix.isNotEmpty() &&
                    adjacentMatrix.all { it.size == adjacentMatrix.size }) {
                "adjacent matrix must be non-empty square matrix"
            }

            when (mode) {
                DfsMode.RECURSIVE -> dfsInternalByRecursive(
                    adjacentMatrix,
                    startIndex,
                    visitProcess,
                    finishProcess
                )

                DfsMode.STACK -> dfsInternalByStack(
                    adjacentMatrix,
                    startIndex,
                    visitProcess
                )

                DfsMode.STACK_NOT_EFFECTIVELY -> dfsInternalByStackNotEffectively(
                    adjacentMatrix,
                    startIndex,
                    visitProcess
                )
            }
        }

        private fun dfsInternalByStack(
            adjacentMatrix: List<List<Int>>,
            startIndex: Int,
            visitProcess: Consumer<Int> = Consumer { }
        ) {
            val isVisited = MutableList(adjacentMatrix.size) { false }

            val stack = Stack<Int>()
            stack.push(startIndex)
            isVisited[startIndex] = true

            while (stack.isNotEmpty()) {
                val currIndex = stack.pop()

                visitProcess.accept(currIndex)

                for (nextIndex in adjacentMatrix[currIndex].indices.reversed()) {
                    if (adjacentMatrix[currIndex][nextIndex] == 0 || isVisited[nextIndex]) {
                        continue
                    }

                    stack.push(nextIndex)
                    isVisited[nextIndex] = true
                }
            }
        }

        private fun dfsInternalByStackNotEffectively(
            adjacentMatrix: List<List<Int>>,
            startIndex: Int,
            visitProcess: Consumer<Int> = Consumer { }
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
                visitProcess.accept(currIndex)

                for (nextIndex in adjacentMatrix[currIndex].indices.reversed()) {
                    if (adjacentMatrix[currIndex][nextIndex] == 0 || isVisited[nextIndex]) {
                        continue
                    }

                    stack.push(nextIndex)
                }
            }
        }


        private fun dfsInternalByRecursive(
            adjacentMatrix: List<List<Int>>,
            currIndex: Int,
            visitProcess: Consumer<Int> = Consumer { },
            finishProcess: Consumer<Int> = Consumer { },
            isVisited: MutableList<Boolean> = MutableList(adjacentMatrix.size) { false },
            isFinished: MutableList<Boolean> = MutableList(adjacentMatrix.size) { false },
        ) {
            if (isVisited[currIndex]) {
                return
            }

            isVisited[currIndex] = true
            visitProcess.accept(currIndex)

            for (nextIndex in adjacentMatrix[currIndex].indices) {
                if (adjacentMatrix[currIndex][nextIndex] == 0 || isVisited[nextIndex]) {
                    continue
                }

                dfsInternalByRecursive(
                    adjacentMatrix, nextIndex, visitProcess, finishProcess, isVisited, isFinished
                )
            }

            isFinished[currIndex] = true
            finishProcess.accept(currIndex)
        }
    }
}


fun main() {

    val graph = listOf(
        listOf(0, 1, 1, 0, 0),
        listOf(1, 0, 0, 1, 0),
        listOf(1, 0, 0, 0, 1),
        listOf(0, 1, 0, 0, 0),
        listOf(0, 0, 1, 0, 0)
    )
    DepthFirstSearch.dfs(graph, 0, { println("$it visited") }, { println("$it finished") })

}
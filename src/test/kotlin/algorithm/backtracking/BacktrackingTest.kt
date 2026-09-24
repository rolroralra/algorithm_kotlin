package algorithm.backtracking

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BacktrackingTest {

    @Test
    fun `전체 탐색 이후 방문 표시는 모두 원상 복구된다`() {
        val graph = listOf(listOf(1, 2), listOf(0, 3), listOf(0), listOf(1))
        val isVisited = MutableList(graph.size) { false }

        backtracking(graph, isVisited, 0)

        assertEquals(List(graph.size) { false }, isVisited)
    }

    @Test
    fun `순환 그래프에서도 무한 재귀 없이 종료한다`() {
        val graph = listOf(listOf(1), listOf(2), listOf(0))
        val isVisited = MutableList(graph.size) { false }

        backtracking(graph, isVisited, 0)

        assertEquals(List(graph.size) { false }, isVisited)
    }

    @Test
    fun `간선이 없는 단일 정점에서도 동작한다`() {
        val isVisited = mutableListOf(false)

        backtracking(listOf(emptyList()), isVisited, 0)

        assertEquals(listOf(false), isVisited)
    }

    @Test
    fun `추가 인자를 전달해도 오류 없이 동작한다`() {
        val graph = listOf(listOf(1), listOf(0))
        val isVisited = MutableList(graph.size) { false }

        backtracking(graph, isVisited, 0, "path", 42)

        assertEquals(List(graph.size) { false }, isVisited)
    }
}

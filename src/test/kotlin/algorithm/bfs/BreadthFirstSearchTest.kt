package algorithm.bfs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class BreadthFirstSearchTest {

    private fun unweighted(adjacent: List<List<Int>>): List<List<Pair<Int, Int>>> =
        adjacent.map { neighbors -> neighbors.map { it to 1 } }

    @Test
    fun `연결된 그래프의 모든 정점을 정확히 한 번씩 방문한다`() {
        val graph = unweighted(listOf(listOf(1, 2), listOf(0, 3), listOf(0, 4), listOf(1), listOf(2)))
        val visited = mutableListOf<Int>()

        bfsWithAdjacentList(graph, 0) { visited.add(it) }

        assertEquals((0..4).toList(), visited.sorted())
        assertEquals(visited.size, visited.toSet().size)
    }

    @Test
    fun `연결되지 않은 정점은 방문하지 않는다`() {
        val graph = unweighted(listOf(listOf(1), listOf(0), listOf(3), listOf(2)))
        val visited = mutableListOf<Int>()

        bfsWithAdjacentList(graph, 0) { visited.add(it) }

        assertEquals(listOf(0, 1), visited.sorted())
    }

    @Test
    fun `간선이 없는 단일 정점을 방문한다`() {
        val visited = mutableListOf<Int>()

        bfsWithAdjacentList(listOf(emptyList()), 0) { visited.add(it) }

        assertEquals(listOf(0), visited)
    }

    @Test
    fun `순환 그래프에서도 무한루프 없이 종료한다`() {
        val graph = unweighted(listOf(listOf(1), listOf(2), listOf(0)))
        val visited = mutableListOf<Int>()

        bfsWithAdjacentList(graph, 0) { visited.add(it) }

        assertEquals(listOf(0, 1, 2), visited.sorted())
    }

    @Test
    fun `임의의 시작 정점에서 순회를 시작할 수 있다`() {
        val graph = unweighted(listOf(listOf(1), listOf(0, 2), listOf(1)))
        val visited = mutableListOf<Int>()

        bfsWithAdjacentList(graph, 2) { visited.add(it) }

        assertEquals(listOf(0, 1, 2), visited.sorted())
    }

    @Test
    fun `유효하지 않은 시작 인덱스는 예외를 던진다`() {
        assertThrows(IllegalArgumentException::class.java) {
            bfsWithAdjacentList(unweighted(listOf(listOf(0))), 5) {}
        }
    }
}

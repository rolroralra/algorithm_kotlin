package algorithm.dfs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class DepthFirstSearchTest {

    private fun unweighted(adjacent: List<List<Int>>): List<List<Pair<Int, Int>>> =
        adjacent.map { neighbors -> neighbors.map { it to 1 } }

    @ParameterizedTest
    @EnumSource(DfsMode::class)
    fun `도달 가능한 모든 정점을 방문한다`(mode: DfsMode) {
        val graph = unweighted(listOf(listOf(1, 2), listOf(0, 3), listOf(0, 4), listOf(1), listOf(2)))
        val visited = mutableListOf<Int>()

        dfsWithAdjacentList(graph, 0, { visited.add(it) }, mode = mode)

        assertEquals((0..4).toList(), visited.sorted())
    }

    @ParameterizedTest
    @EnumSource(DfsMode::class)
    fun `도달 불가능한 정점은 방문하지 않는다`(mode: DfsMode) {
        val graph = unweighted(listOf(listOf(1), listOf(0), listOf(3), listOf(2)))
        val visited = mutableListOf<Int>()

        dfsWithAdjacentList(graph, 0, { visited.add(it) }, mode = mode)

        assertEquals(listOf(0, 1), visited.sorted())
    }

    @ParameterizedTest
    @EnumSource(DfsMode::class)
    fun `간선이 없는 단일 정점을 방문한다`(mode: DfsMode) {
        val visited = mutableListOf<Int>()

        dfsWithAdjacentList(listOf(emptyList()), 0, { visited.add(it) }, mode = mode)

        assertEquals(listOf(0), visited)
    }

    @ParameterizedTest
    @EnumSource(DfsMode::class)
    fun `순환 그래프에서도 무한루프 없이 종료한다`(mode: DfsMode) {
        val graph = unweighted(listOf(listOf(1), listOf(2), listOf(0)))
        val visited = mutableListOf<Int>()

        dfsWithAdjacentList(graph, 0, { visited.add(it) }, mode = mode)

        assertEquals(listOf(0, 1, 2), visited.sorted())
    }

    @ParameterizedTest
    @EnumSource(DfsMode::class)
    fun `임의의 시작 정점에서 순회를 시작할 수 있다`(mode: DfsMode) {
        val graph = unweighted(listOf(listOf(1), listOf(0, 2), listOf(1)))
        val visited = mutableListOf<Int>()

        dfsWithAdjacentList(graph, 2, { visited.add(it) }, mode = mode)

        assertEquals(listOf(0, 1, 2), visited.sorted())
    }
}

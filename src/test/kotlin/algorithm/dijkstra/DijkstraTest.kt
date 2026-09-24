package algorithm.dijkstra

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DijkstraTest {

    companion object {
        @JvmStatic
        fun implementations(): List<(List<List<Pair<Int, Int>>>, Int) -> Pair<List<Long>, List<Int>>> = listOf(
            { graph, start -> dijkstraByPriorityQueue(graph, start) },
            { graph, start -> dijkstraByHeap(graph, start) },
        )

        fun sampleGraph(): List<List<Pair<Int, Int>>> = listOf(
            listOf(1 to 1, 2 to 4),
            listOf(2 to 2),
            listOf(3 to 1),
            emptyList(),
        )
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `시작 정점까지의 거리는 0이다`(dijkstra: (List<List<Pair<Int, Int>>>, Int) -> Pair<List<Long>, List<Int>>) {
        val (distance, _) = dijkstra(sampleGraph(), 0)

        assertEquals(0L, distance[0])
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `최단 거리를 구한다`(dijkstra: (List<List<Pair<Int, Int>>>, Int) -> Pair<List<Long>, List<Int>>) {
        val (distance, _) = dijkstra(sampleGraph(), 0)

        assertEquals(listOf(0L, 1L, 3L, 4L), distance)
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `도달할 수 없는 정점은 거리가 무한대로 유지된다`(dijkstra: (List<List<Pair<Int, Int>>>, Int) -> Pair<List<Long>, List<Int>>) {
        val graph = listOf(listOf(1 to 1), emptyList(), emptyList())

        val (distance, _) = dijkstra(graph, 0)

        assertEquals(Long.MAX_VALUE, distance[2])
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `최단 경로를 복원한다`(dijkstra: (List<List<Pair<Int, Int>>>, Int) -> Pair<List<Long>, List<Int>>) {
        val (_, prevIndex) = dijkstra(sampleGraph(), 0)

        assertEquals(listOf(0, 1, 2, 3), shortestPath(prevIndex, 3))
    }

    @Test
    fun `두 구현의 거리 계산 결과는 같다`() {
        val (byPriorityQueue, _) = dijkstraByPriorityQueue(sampleGraph(), 0)
        val (byHeap, _) = dijkstraByHeap(sampleGraph(), 0)

        assertEquals(byPriorityQueue, byHeap)
    }
}

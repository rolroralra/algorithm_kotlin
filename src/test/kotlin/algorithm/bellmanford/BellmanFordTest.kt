package algorithm.bellmanford

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BellmanFordTest {

    @Test
    fun `시작 정점까지의 거리는 0이다`() {
        val (distance, _, _) = bellmanFord(listOf(Triple(0, 1, 1), Triple(1, 2, 2)), 0, vertexSize = 3)

        assertEquals(0L, distance[0])
    }

    @Test
    fun `최단 거리를 구한다`() {
        val edges = listOf(Triple(0, 1, 1), Triple(1, 2, 2), Triple(0, 2, 4), Triple(2, 3, 1))

        val (distance, _, hasNegativeCycle) = bellmanFord(edges, 0, vertexSize = 4)

        assertEquals(listOf(0L, 1L, 3L, 4L), distance)
        assertFalse(hasNegativeCycle)
    }

    @Test
    fun `최단 경로를 복원한다`() {
        val edges = listOf(Triple(0, 1, 1), Triple(1, 2, 2), Triple(0, 2, 4), Triple(2, 3, 1))

        val (_, prevIndex, _) = bellmanFord(edges, 0, vertexSize = 4)

        assertEquals(listOf(0, 1, 2, 3), shortestPath(prevIndex, 3))
    }

    @Test
    fun `음수 가중치 간선도 처리한다`() {
        val edges = listOf(Triple(0, 1, 4), Triple(0, 2, 5), Triple(1, 2, -3))

        val (distance, _, hasNegativeCycle) = bellmanFord(edges, 0, vertexSize = 3)

        assertEquals(listOf(0L, 4L, 1L), distance)
        assertFalse(hasNegativeCycle)
    }

    @Test
    fun `음수 사이클을 감지한다`() {
        val edges = listOf(Triple(0, 1, 1), Triple(1, 2, -3), Triple(2, 0, 1))

        val (_, _, hasNegativeCycle) = bellmanFord(edges, 0, vertexSize = 3)

        assertTrue(hasNegativeCycle)
    }

    @Test
    fun `도달할 수 없는 정점은 거리가 무한대로 유지된다`() {
        val edges = listOf(Triple(0, 1, 1), Triple(2, 3, 1))

        val (distance, _, _) = bellmanFord(edges, 0, vertexSize = 4)

        assertEquals(Long.MAX_VALUE, distance[2])
        assertEquals(Long.MAX_VALUE, distance[3])
    }
}

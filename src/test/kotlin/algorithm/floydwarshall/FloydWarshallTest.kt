package algorithm.floydwarshall

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FloydWarshallTest {

    private val infinity = Long.MAX_VALUE / 2

    private fun buildMatrix(n: Int, edges: List<Triple<Int, Int, Int>>): List<List<Long>> {
        val matrix = MutableList(n) { MutableList(n) { infinity } }
        edges.forEach { (a, b, weight) -> matrix[a][b] = weight.toLong() }
        return matrix
    }

    @Test
    fun `자기 자신까지의 거리는 0이다`() {
        val matrix = buildMatrix(3, listOf(Triple(0, 1, 1), Triple(1, 2, 1)))

        val (distance, _) = floydWarshall(matrix)

        assertEquals(0L, distance[0][0])
        assertEquals(0L, distance[1][1])
        assertEquals(0L, distance[2][2])
    }

    @Test
    fun `경유지를 거친 최단 거리를 구한다`() {
        val matrix = buildMatrix(4, listOf(Triple(0, 1, 1), Triple(1, 2, 2), Triple(0, 2, 4), Triple(2, 3, 1)))

        val (distance, _) = floydWarshall(matrix)

        assertEquals(3L, distance[0][2])
        assertEquals(4L, distance[0][3])
    }

    @Test
    fun `도달할 수 없는 쌍은 거리가 무한대로 유지된다`() {
        val matrix = buildMatrix(3, listOf(Triple(0, 1, 1)))

        val (distance, _) = floydWarshall(matrix)

        assertEquals(infinity, distance[0][2])
        assertEquals(infinity, distance[2][0])
    }

    @Test
    fun `최단 경로를 복원한다`() {
        val matrix = buildMatrix(4, listOf(Triple(0, 1, 1), Triple(1, 2, 2), Triple(0, 2, 4), Triple(2, 3, 1)))

        val (_, prevIndex) = floydWarshall(matrix)

        assertEquals(listOf(0, 1, 2, 3), shortestPath(prevIndex, 0, 3))
    }

    @Test
    fun `경로가 없으면 빈 리스트를 반환한다`() {
        val matrix = buildMatrix(3, listOf(Triple(0, 1, 1)))

        val (_, prevIndex) = floydWarshall(matrix)

        assertEquals(emptyList<Int>(), shortestPath(prevIndex, 0, 2))
    }

    @Test
    fun `더 짧은 직접 간선을 우회 경로보다 우선한다`() {
        val matrix = buildMatrix(3, listOf(Triple(0, 1, 1), Triple(1, 2, 1), Triple(0, 2, 1)))

        val (distance, _) = floydWarshall(matrix)

        assertEquals(1L, distance[0][2])
    }
}

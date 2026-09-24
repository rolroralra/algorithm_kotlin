package algorithm.eratosthenes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

class ErathosthenesSieveTest {

    private fun naivePrimes(maxNumber: Int): List<Int> =
        (2..maxNumber).filter { n -> (2..sqrt(n.toDouble()).toInt()).none { n % it == 0 } }

    @Test
    fun `2보다 작으면 소수가 없다`() {
        assertEquals(emptyList<Int>(), eratosthenesSieve(1))
    }

    @Test
    fun `가장 작은 소수는 2다`() {
        assertEquals(listOf(2), eratosthenesSieve(2))
    }

    @Test
    fun `10 이하의 소수를 구한다`() {
        assertEquals(listOf(2, 3, 5, 7), eratosthenesSieve(10))
    }

    @Test
    fun `단순 소수 판별 결과와 일치한다`() {
        listOf(30, 50, 100, 200).forEach { maxNumber ->
            assertEquals(naivePrimes(maxNumber), eratosthenesSieve(maxNumber))
        }
    }

    @Test
    fun `2보다 작은 수는 소수가 아니다`() {
        assertFalse(isPrime(0))
        assertFalse(isPrime(1))
    }

    @Test
    fun `알려진 소수를 소수로 판별한다`() {
        listOf(2, 3, 5, 7, 11, 97).forEach { assertTrue(isPrime(it)) }
    }

    @Test
    fun `알려진 합성수를 소수가 아니라고 판별한다`() {
        listOf(4, 6, 8, 9, 15, 100).forEach { assertFalse(isPrime(it)) }
    }
}

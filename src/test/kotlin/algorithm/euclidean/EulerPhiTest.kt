package algorithm.euclidean

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EulerPhiTest {

    @Test
    fun `알려진 값에 대해 오일러 파이 함수를 계산한다`() {
        assertEquals(24, EulerPhi()(45))
    }

    @Test
    fun `1의 오일러 파이 값은 1이다`() {
        assertEquals(1, EulerPhi()(1))
    }

    @Test
    fun `소수의 오일러 파이 값은 자기 자신 빼기 1이다`() {
        assertEquals(16, EulerPhi()(17))
    }

    @Test
    fun `소인수분해 기반 계산도 같은 값을 반환한다`() {
        assertEquals(24, EulerPhi().phiByFactorization(45))
    }

    @Test
    fun `소인수분해 기반 계산과 체 기반 계산은 일치한다`() {
        val phi = EulerPhi()

        (2 until 100).forEach { n ->
            assertEquals(phi(n), phi.phiByFactorization(n))
        }
    }
}

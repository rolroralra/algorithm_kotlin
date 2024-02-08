package leetcode

fun main() {
    val twoSum = TwoSum()

    twoSum.add(1)
    twoSum.add(3)
    twoSum.add(5)

    twoSum.find(4).also { println(it) }
    twoSum.find(7).also { println(it) }
}

class TwoSum() {
    private val numbers = mutableListOf<Int>()

    fun add(number: Int) {
        numbers.add(number)
    }

    fun find(value: Int): Boolean {
        val numberCache = mutableSetOf<Int>()

        for (index in numbers.indices) {
            val targetRemainNumber = value - numbers[index]

            if (numberCache.contains(targetRemainNumber)) {
                return true
            }

            numberCache.add(numbers[index])
        }

        return false
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * var obj = TwoSum()
 * obj.add(number)
 * var param_2 = obj.find(value)
 */

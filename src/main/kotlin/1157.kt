fun main() {
    var maxChar: Char? = null
    var maxCount = Int.MIN_VALUE
    val countMap = HashMap<Char, Int>()

    readLine()?.lowercase()?.forEach {
        countMap[it] = (countMap[it] ?: 0) + 1
    }

    var isEqual = false
    for ((key, value) in countMap) {
        if (value > maxCount) {
            maxChar = key
            maxCount = value
            isEqual = false
        } else if (value == maxCount){
            isEqual = true
        }
    }

    if (isEqual) {
        println("?")
    } else {
        println(maxChar!!.uppercase())
    }

}
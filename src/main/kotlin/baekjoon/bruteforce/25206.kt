package baekjoon.bruteforce

fun main() {
    val br = System.`in`.bufferedReader()

    var subjectScaleSum = 0
    var subjectScoreSum = 0.0

    (1..20).forEach { _ ->
        val inputs = br.readLine().split(" ")

        val subjectScale = inputs[1]
        val subjectScore = inputs[2]

        if (isValidSubject(subjectScore)) {
            val (scale, score) = getSubjectScaleAndScore(subjectScale, subjectScore)
            subjectScaleSum += scale
            subjectScoreSum += score
        }
    }

    val result = subjectScoreSum / subjectScaleSum

    println("%.6f".format(result))
}

private fun isValidSubject(subjectScore: String): Boolean = subjectScore != "P"

private fun getSubjectScaleAndScore(subjectScale: String, subjectScore: String): Pair<Int, Double> {
    return getSubjectScale(subjectScale) to getSubjectScore(subjectScale, subjectScore)
}

private fun getSubjectScore(subjectScale: String, subjectScore: String): Double {
    return getScore(subjectScore) * getSubjectScale(subjectScale)
}

private fun getSubjectScale(subjectScale: String): Int =
    when (subjectScale) {
        "4.0" -> 4
        "3.0" -> 3
        "2.0" -> 2
        "1.0" -> 1
        else -> throw IllegalArgumentException("Invalid subject scale ${subjectScale}")
    }

private fun getScore(subjectScore: String): Double =
    when (subjectScore) {
        "A+" -> 4.5
        "A0" -> 4.0
        "B+" -> 3.5
        "B0" -> 3.0
        "C+" -> 2.5
        "C0" -> 2.0
        "D+" -> 1.5
        "D0" -> 1.0
        "F" -> 0.0
        else -> throw IllegalArgumentException("Invalid Subject Score $subjectScore")
    }

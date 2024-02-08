package leetcode

private fun read4(buf4:CharArray): Int { return buf4.take(4).joinToString("").trim().length }

private fun read(buf:CharArray, n:Int): Int {
        var totalRead = 0
        val buf4 = CharArray(4)

        var read4Result = read4(buf4)
        while (read4Result > 0 && totalRead < n) {
            val read = minOf(read4Result, n - totalRead)
            buf4.copyInto(buf, totalRead, 0, read)
            totalRead += read
            read4Result = read4(buf4)
        }

        return totalRead
}

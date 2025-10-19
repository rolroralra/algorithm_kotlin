package algorithm.unionfind

open class UnionFind(val size: Int) {

    private val parent = MutableList(size) { -1 }

    init {
        require(size > 0) { "Size must be greater than zero." }
    }

    fun union(firstIndex: Int, secondIndex: Int) {
        require(firstIndex in 0 until size) { "firstIndex out of range: $firstIndex" }
        require(secondIndex in 0 until size) { "secondIndex out of range: $secondIndex" }

        var firstParent = find(firstIndex)
        var secondParent = find(secondIndex)

        if (firstParent == secondParent) {
            return
        }

        val firstGroupSize = groupSize(firstParent)
        val secondGroupSize = groupSize(secondParent)

        if (firstGroupSize < secondGroupSize) {
            firstParent = secondParent.also { secondParent = firstParent }
        }

        parent[firstParent] += parent[secondParent]
        parent[secondParent] = firstParent
    }

    fun find(index: Int): Int {
        require(index in 0 until size) { "index out of range: $index" }

        if (parent[index] < 0) {
            return index
        }

        parent[index] = find(parent[index])
        return parent[index]
    }

    @Suppress("UNUSED")
    fun isConnected(firstIndex: Int, secondIndex: Int): Boolean {
        require(firstIndex in 0 until size) { "firstIndex out of range: $firstIndex" }
        require(secondIndex in 0 until size) { "secondIndex out of range: $secondIndex" }

        return find(secondIndex) == find(firstIndex)
    }

    fun getInfo(): Map<Int, List<Int>> = (0 until size).groupBy { find(it) }

    fun printInfo() {
        println(getInfo())
    }

    private fun groupSize(index: Int): Int {
        return -1 * parent[find(index)]
    }

}

fun main() {
    val unionFind = UnionFind(6)

    unionFind.printInfo()
    unionFind.union(0, 1)
    unionFind.printInfo()
    unionFind.union(3, 4)
    unionFind.printInfo()
    unionFind.union(1, 5)
    unionFind.printInfo()
    unionFind.union(2, 0)
    unionFind.printInfo()
    unionFind.union(5, 4)
    unionFind.printInfo()
}

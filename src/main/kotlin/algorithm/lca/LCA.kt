package algorithm.lca

/**
 * Lowest Common Ancestor. Build once via [build], then query with [lca].
 */
class LCA(private val size: Int) {
    fun build(adjacentList: List<List<Int>>, rootIndex: Int = 0) {
        TODO("Implement LCA preprocessing (e.g. binary lifting) from an adjacency list")
    }

    fun getDepth(index: Int): Int {
        TODO("Implement depth lookup for a node built by build()")
    }

    fun lca(firstIndex: Int, secondIndex: Int): Int {
        TODO("Implement lowest common ancestor query")
    }
}

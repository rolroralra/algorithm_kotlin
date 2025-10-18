package algorithm.heap

import algorithm.binarytree.BinaryTreeNode

open class HuffmanCode {
    companion object {
        fun huffmanCodes(wordFrequencies: List<WordFrequency>): Map<String, String> {
            val heap = Heap(wordFrequencies)
            val wordFrequencyToNode =
                wordFrequencies.associateWith { BinaryTreeNode(it) }.toMutableMap()

            lateinit var rootNode: BinaryTreeNode<WordFrequency>

            while (heap.size() > 1) {
                val first = heap.poll()
                val second = heap.poll()
                val newWordFrequency = WordFrequency.merge(first, second)

                val firstNode = wordFrequencyToNode[first]
                    ?: throw IllegalArgumentException("First node not found")
                val secondNode = wordFrequencyToNode[second]
                    ?: throw IllegalArgumentException("Second node not found")

                val newNode =
                    BinaryTreeNode(newWordFrequency, firstNode, secondNode)

                wordFrequencyToNode[newWordFrequency] = newNode
                rootNode = newNode

                heap.add(newWordFrequency)
            }

            val huffmanCodes = mutableMapOf<String, String>()
            takeHuffmanCodes(rootNode, huffmanCodes)

            return huffmanCodes
        }
    }
}

private fun takeHuffmanCodes(
    node: BinaryTreeNode<WordFrequency>,
    huffmanCodes: MutableMap<String, String>,
    prefixHuffmanCode: String = ""
) {
    if (node.isLeafNode()) {
        huffmanCodes.put(node.`val`.word, prefixHuffmanCode)
        return
    }

    node.left?.let { takeHuffmanCodes(it, huffmanCodes, "${prefixHuffmanCode}1") }
    node.right?.let { takeHuffmanCodes(it, huffmanCodes, "${prefixHuffmanCode}0") }
}

class WordFrequency(val word: String, val frequency: Int) : Comparable<WordFrequency> {
    override fun compareTo(other: WordFrequency): Int {
        return if (frequency == other.frequency) word.compareTo(other.word)
        else frequency.compareTo(other.frequency)
    }

    companion object {
        fun merge(first: WordFrequency, second: WordFrequency): WordFrequency {
            return WordFrequency("${first.word}+${second.word}", first.frequency + second.frequency)
        }
    }
}

fun main() {
    val wordFrequencies = listOf(
        WordFrequency("s", 4),
        WordFrequency("i", 6),
        WordFrequency("n", 8),
        WordFrequency("t", 12),
        WordFrequency("e", 15),
        WordFrequency("k", 15),
    )

    val result = HuffmanCode.huffmanCodes(wordFrequencies)

    println(result)
}
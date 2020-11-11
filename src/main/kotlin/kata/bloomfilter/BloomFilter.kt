package kata.bloomfilter

import java.util.*

class BloomFilter(val hashAlgorithm: HashAlgorithm) {
    constructor() : this(SimpleHashAlgorithm())

    private val bitField = BitSet()

    fun add(word: String) {
        word.ifEmpty { throw IllegalArgumentException() }
        bitField.set(hashAlgorithm.hash(word) % bitField.size())
    }

    fun isProbablyKnown(word: String): Boolean {
        return bitField.get(hashAlgorithm.hash(word) % bitField.size())
    }

}

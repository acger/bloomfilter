package kata.bloomfilter

import java.util.*

class BloomFilter(bitfieldSize: Int, private val hashAlgorithm: HashAlgorithm) {
    constructor() : this(1024, SimpleHashAlgorithm())

    private val bitField = BitSet(bitfieldSize)

    fun add(word: String) {
        word.ifEmpty { throw IllegalArgumentException() }
        bitField.set(hashAlgorithm.hash(word) % bitField.size())
    }

    fun isProbablyKnown(word: String): Boolean {
        return bitField.get(hashAlgorithm.hash(word) % bitField.size())
    }

}

package de.acger.bloomfilter

import de.acger.bloomfilter.hashfunctions.HashAlgorithm
import de.acger.bloomfilter.hashfunctions.Murmur3Hash
import java.util.*

class BloomFilter(private val bitfieldSize: Int, private vararg val hashAlgorithm: HashAlgorithm) {
    constructor() : this(1024, Murmur3Hash(1))

    private val bitField = BitSet(bitfieldSize)

    fun add(word: String) {
        word.ifEmpty { throw IllegalArgumentException() }
        hashAlgorithm.forEach { bitField.set(it.hash(word) % bitfieldSize) }
    }

    fun add(wordList: Set<String>) {
        wordList.forEach { add(it) }
    }

    fun isProbablyKnown(word: String): Boolean {
        return hashAlgorithm.all { bitField.get(it.hash(word) % bitfieldSize) }
    }
}

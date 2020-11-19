package de.acger.bloomfilter.hashfunctions

interface HashAlgorithm {
    fun hash(word: String) : Int
}

package de.acger.bloomfilter.hashfunctions

import com.google.common.hash.Hashing
import kotlin.math.absoluteValue

@Suppress("UnstableApiUsage")
class Murmur3Hash(val seed: Int) : HashAlgorithm {
    override fun hash(word: String): Int {
        return Hashing.murmur3_32(seed).hashBytes(word.toByteArray(Charsets.UTF_8)).asInt().absoluteValue
    }
}

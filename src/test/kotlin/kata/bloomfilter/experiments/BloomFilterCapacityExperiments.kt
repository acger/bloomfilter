package kata.bloomfilter.experiments

import kata.bloomfilter.BloomFilter
import kata.hashfunctions.Murmur3Hash
import org.junit.jupiter.api.Test

class BloomFilterCapacityExperiments {

    @Test
    fun `create plot data`() {
        data class TestData(val bits: Int, val wordCount: Int, val falsePositives: Long)

        val randomWordListGenerator = RandomWordListGenerator()
        val test = randomWordListGenerator.wordList(10000)

        val data = mutableListOf<TestData>()
        for (wordCount in 1..10000 step 100) {
            val wordList = randomWordListGenerator.wordList(wordCount)
            for (bits in 1..4096 step 32) {
                val bloomFilter = BloomFilter(bits, Murmur3Hash(1), Murmur3Hash(2))
                bloomFilter.add(wordList)
                val falsePositives = test.parallelStream().filter({ bloomFilter.isProbablyKnown(it) }).count()
                data.add(TestData(bits, wordCount, falsePositives))
            }
        }
        data.size
    }
}

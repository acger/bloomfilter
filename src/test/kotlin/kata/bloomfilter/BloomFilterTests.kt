package kata.bloomfilter

import io.mockk.every
import io.mockk.mockk
import kata.hashfunctions.HashAlgorithm
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BloomFilterTests {
    @Test
    fun `do not empty string`() {
        val bloomFilter = BloomFilter()
        assertThrows<IllegalArgumentException> { bloomFilter.add("") }
    }

    @Test
    fun `add known word accepts non empty string`() {
        val bloomFilter = BloomFilter()
        bloomFilter.add("abc")
    }

    @Test
    fun `filter recognizes known word`() {
        val hashAlgorithm = mockk<HashAlgorithm>()
        every { hashAlgorithm.hash("abc") } returns 2

        val bloomFilter = BloomFilter(1024, hashAlgorithm)
        bloomFilter.add("abc")

        assertTrue(bloomFilter.isProbablyKnown("abc"))
    }

    @Test
    fun `filter rejects unknown word`() {
        val hashAlgorithm = mockk<HashAlgorithm>()
        every { hashAlgorithm.hash("abc") } returns 2
        every { hashAlgorithm.hash("cba") } returns 1

        val bloomFilter = BloomFilter(1024, hashAlgorithm)
        bloomFilter.add("abc")

        assertFalse(bloomFilter.isProbablyKnown("cba"))
    }
}

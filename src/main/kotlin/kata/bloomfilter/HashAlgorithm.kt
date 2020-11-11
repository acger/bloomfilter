package kata.bloomfilter

interface HashAlgorithm {
    fun hash(word: String) : Int
}

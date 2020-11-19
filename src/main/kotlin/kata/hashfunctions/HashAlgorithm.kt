package kata.hashfunctions

interface HashAlgorithm {
    fun hash(word: String) : Int
}

package de.acger.bloomfilter.experiments

import kotlin.random.Random

class RandomWordListGenerator(var stringLength: Int = 10) {
    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    private fun newWord(): String {
        return (1..stringLength)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun wordList(size: Int): Set<String> {
        val words = mutableSetOf<String>()
        repeat(size) { words.add(newWord()) }
        return words
    }
}

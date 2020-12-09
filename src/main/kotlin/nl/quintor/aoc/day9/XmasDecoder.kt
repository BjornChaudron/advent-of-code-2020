package nl.quintor.aoc.day9

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day9", "input.txt")
    val maxPreambleSize = 25
    val xmasDecoder = XmasDecoder(path, maxPreambleSize)
    val sequenceBreaker = xmasDecoder.findSequenceBreaker()
    println("The number that broke the sequence is $sequenceBreaker")
}

class XmasDecoder(
    private val path: Path,
    var maxPreambleSize: Int = 0,
    private val fileReader: FileReader = FileReader()
) {

    fun findSequenceBreaker(): Long {
        val sequence = readFile()
        val preamble = ArrayDeque<Long>()

        sequence.take(maxPreambleSize).forEach { preamble.addLast(it) }

        val remainingIndices = maxPreambleSize..sequence.lastIndex

        for (index in remainingIndices) {
            val nextValue = sequence[index]

            val temp = preamble.toList()

            val isValid = preamble.any { value ->
                temp.contains(nextValue - value)
            }

            if (!isValid) return nextValue

            preamble.removeFirst()
            preamble.addLast(nextValue)
        }

        return 0
    }

    private fun readFile(): List<Long> {
        return fileReader
            .readAllLines(path)
            .map { s -> s.toLong() }
    }
}
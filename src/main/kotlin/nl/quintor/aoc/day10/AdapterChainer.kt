package nl.quintor.aoc.day10

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day10", "input.txt")
    val adapterChainer = AdapterChainer(path)

    val distribution = adapterChainer.getDistribution()
    val product = distribution.singles * distribution.triples

    println("The product of singles * triples = $product")
}

class AdapterChainer(private val path: Path, private val fileReader: FileReader = FileReader()) {
    fun getDistribution(): Distribution {
        val adapters = mutableListOf(0L)

        fileReader.readAllLines(path)
            .map { it.toLong() }
            .sorted()
            .forEach { adapters.add(it) }

        val builtInAdapter = adapters.maxOrNull()!!.plus(3)
        adapters.add(builtInAdapter)

        val distribution = Distribution()

        for (i in 0 until adapters.lastIndex) {
            val currentValue = adapters[i]
            val nextValue = adapters[i + 1]

            when(nextValue - currentValue) {
                1L -> distribution.singles++
                2L -> distribution.doubles++
                3L -> distribution.triples++
            }
        }

        return distribution
    }
}

data class Distribution(var singles: Int = 0, var doubles: Int = 0, var triples: Int = 0)
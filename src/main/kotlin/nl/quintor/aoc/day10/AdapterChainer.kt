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

    val nrOfPossibilities = adapterChainer.getNrOfPossibleAdapterCombinations()
    println("Amount of possible paths = $nrOfPossibilities")
}

class AdapterChainer(private val path: Path, private val fileReader: FileReader = FileReader()) {
    fun getDistribution(): Distribution {
        val adapters = prepareInput()

        val distribution = Distribution()

        for (i in 0 until adapters.lastIndex) {
            val currentValue = adapters[i]
            val nextValue = adapters[i + 1]

            when (nextValue - currentValue) {
                1L -> distribution.singles++
                2L -> distribution.doubles++
                3L -> distribution.triples++
            }
        }

        return distribution
    }

    private fun prepareInput(): MutableList<Long> {
        val adapters = fileReader
            .readAllLines(path)
            .map { it.toLong() }

        val builtInAdapter = adapters.maxOrNull()!! + 3

        return adapters
            .plus(0)
            .plus(builtInAdapter)
            .sorted()
            .toMutableList()
    }

    fun getNrOfPossibleAdapterCombinations(): Long {
        val adapters = prepareInput()

        val pathsByAdapter: MutableMap<Long, Long> = mutableMapOf(0L to 1L)

        adapters.drop(1).forEach { adapter ->
            pathsByAdapter[adapter] = (1..3).map { lookBack ->
                pathsByAdapter.getOrDefault(adapter - lookBack, 0)
            }.sum()
        }

        return pathsByAdapter.getValue(adapters.last())
    }
}

data class Distribution(var singles: Int = 0, var doubles: Int = 0, var triples: Int = 0)
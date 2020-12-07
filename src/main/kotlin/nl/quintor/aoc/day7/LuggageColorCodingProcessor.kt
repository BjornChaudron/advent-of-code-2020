package nl.quintor.aoc.day7

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path

class LuggageColorCodingProcessor(private val path: Path, private val fileReader: FileReader = FileReader()) {

    fun getNrOfBagsContainingGoldBag(): Int {
        val rules = fileReader.readAllLines(path)

        return 0
    }

    private fun parseRule(rule: String) {
        val pattern = """(?<name>\w* \w*) bags contain (?<nrFirst>\d*) (?<first>\w* \w*) bags?(, (?<nrSecond>\d*) (?<second>\w* \w*))? bags""".toRegex()
    }
}
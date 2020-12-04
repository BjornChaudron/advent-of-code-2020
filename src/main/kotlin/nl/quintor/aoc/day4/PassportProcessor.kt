package nl.quintor.aoc.day4

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

typealias PassportEntry = Map<String, String?>

fun main() {
    val path = Paths.get("src", "main", "resources", "day4", "input.txt")
    val passportProcessor = PassportProcessor(path)
    val nrOfValidPasswords = passportProcessor.countPasswordsWithAllRequiredFields()

    println("$nrOfValidPasswords passwords contain all required fields!")
}

class PassportProcessor(
    private val path: Path,
    private val fileReader: PassportFileReader = PassportFileReader(),
    private val passportFileParser: PassportFileParser = PassportFileParser(),
    private val passportValidator: PassportValidator = PassportValidator()
) {
    fun countPasswordsWithAllRequiredFields(): Int {
        val content = fileReader.readFile(path)
        val passportEntries = passportFileParser.parsePassportEntries(content)

        return passportEntries.count { entry -> passportValidator.containsAllRequiredFields(entry) }
    }
}

class PassportFileReader {
    fun readFile(path: Path): String {
        return Files.readString(path)
    }
}

class PassportFileParser {
    fun parsePassportEntries(content: String): List<PassportEntry> {
        return splitPassportEntries(content)
            .map { entry -> removeNewlineCharacters(entry) }
            .map { entry -> parsePassportEntry(entry) }
    }

    private fun splitPassportEntries(content: String): List<String> {
        return content.split("\n\n")
    }

    private fun removeNewlineCharacters(line: String): String = line.replace("\n", " ")

    private fun parsePassportEntry(entry: String): PassportEntry {
        val columns = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")

        val values = mutableMapOf<String, String?>()

        columns.forEach { column ->
            val regex = """$column:[\w#]*""".toRegex()
            val match = regex.find(entry)
            values[column] = match?.value
        }

        return values
    }
}

class PassportValidator {
    private val requiredColumns = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    fun containsAllRequiredFields(passportEntry: PassportEntry): Boolean {
        return requiredColumns.all { column -> passportEntry[column] != null }
    }
}

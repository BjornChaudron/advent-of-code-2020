package nl.quintor.aoc.day4

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

typealias PassportEntry = Map<String, String?>

fun main() {
    val path = Paths.get("src", "main", "resources", "day4", "input.txt")
    val passportProcessor = PassportProcessor(path)
    val passwordEntriesWithAllRequiredFields = passportProcessor.getPasswordsWithAllRequiredFields()

    println("${passwordEntriesWithAllRequiredFields.size} passwords contain all required fields!")

    val validPasswords = passportProcessor.validatePasswordEntries(passwordEntriesWithAllRequiredFields)

    println("${validPasswords.size} passwords are valid!")
}

class PassportProcessor(
    private val path: Path,
    private val fileReader: PassportFileReader = PassportFileReader(),
    private val passportFileParser: PassportFileParser = PassportFileParser(),
    private val passportValidator: PassportValidator = PassportValidator()
) {
    fun getPasswordsWithAllRequiredFields(): List<PassportEntry> {
        val content = fileReader.readFile(path)
        val passportEntries = passportFileParser.parsePassportEntries(content)

        return passportEntries.filter { entry -> passportValidator.containsAllRequiredFields(entry) }
    }

    fun validatePasswordEntries(passwordEntries: List<PassportEntry>): List<Passport> {
        return passwordEntries
            .map { entry -> Passport.fromPassportEntry(entry) }
            .filter { passport -> passportValidator.isValid(passport) }
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
            val regex = """$column:(?<value>[\w#]*)""".toRegex()
            val match = regex.find(entry)
            values[column] = match?.groups?.get("value")?.value
        }

        return values
    }
}

class PassportValidator {
    private val requiredColumns = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    private val eyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    fun containsAllRequiredFields(passportEntry: PassportEntry): Boolean {
        return requiredColumns.all { column -> passportEntry[column] != null }
    }

    fun isValid(passport: Passport): Boolean {
        return validateBirthYear(passport.birthYear) &&
                validateIssueYear(passport.issueYear) &&
                validateExpirationYear(passport.expirationYear) &&
                validateHeight(passport.height) &&
                validateHairColor(passport.hairColor) &&
                validateEyeColor(passport.eyeColor) &&
                validatePassportId(passport.passportId)
    }

    private fun validateBirthYear(birthYear: Int): Boolean = birthYear in 1920..2002

    private fun validateIssueYear(issueYear: Int): Boolean = issueYear in 2010..2020

    private fun validateExpirationYear(expirationYear: Int): Boolean = expirationYear in 2020..2030

    private fun validateHeight(height: String): Boolean {
        return when {
            height.endsWith("cm") -> height.replace("cm", "").toInt() in 150..193
            height.endsWith("in") -> height.replace("in", "").toInt() in 59..76
            else -> false
        }
    }

    private fun validateHairColor(hairColor: String): Boolean = hairColor.matches("""#[0-9a-f]{6}""".toRegex())

    private fun validateEyeColor(eyeColor: String): Boolean = eyeColor.toLowerCase() in eyeColors;

    private fun validatePassportId(passportId: String): Boolean = passportId.matches("""\d{9}""".toRegex())
}

data class Passport(
    val birthYear: Int,
    val issueYear: Int,
    val expirationYear: Int,
    val height: String,
    val hairColor: String,
    val eyeColor: String,
    val passportId: String,
    val countryId: String?
) {
    companion object {
        fun fromPassportEntry(passportEntry: PassportEntry): Passport {
            return Passport(
                passportEntry["byr"]!!.toInt(),
                passportEntry["iyr"]!!.toInt(),
                passportEntry["eyr"]!!.toInt(),
                passportEntry["hgt"]!!,
                passportEntry["hcl"]!!,
                passportEntry["ecl"]!!,
                passportEntry["pid"]!!,
                passportEntry["cid"]
            )
        }
    }
}
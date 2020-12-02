package nl.quintor.aoc.day2

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day2", "input.txt")
    val passwordValidator = PasswordValidator(path)

    println("Validating passwords in occurence mode.. ")
    val nrOfValidPasswordsInOccurrenceMode = passwordValidator.findNrOfValidPasswords(ValidationMode.OCCURRENCE)
    println("Amount of valid passwords: $nrOfValidPasswordsInOccurrenceMode")

    println()

    println("Validating passwords in occurence mode.. ")
    val nrOfValidPasswordsInPositionalMode = passwordValidator.findNrOfValidPasswords(ValidationMode.POSITIONAL)
    println("Amount of valid passwords: $nrOfValidPasswordsInPositionalMode")
}

class PasswordValidator(private val path: Path, private val fileReader: FileReader = FileReader()) {

    fun findNrOfValidPasswords(validationMode: ValidationMode): Int {
        val lines = fileReader.readFile(path)
        val passwordPolicies = lines.map { line -> PasswordPolicy.parsePasswordPolicy(line) }

        val validator = getValidator(validationMode)

        return passwordPolicies.count { passwordPolicy ->  validator(passwordPolicy) }
    }

    private fun getValidator(validationMode: ValidationMode): (PasswordPolicy) -> Boolean {
        return when (validationMode) {
            ValidationMode.OCCURRENCE -> OccurrenceValidator()
            ValidationMode.POSITIONAL -> PositionalValidator()
        }
    }
}

enum class ValidationMode { OCCURRENCE, POSITIONAL }

class PasswordPolicy(val x: Int, val y: Int, val char: Char, val password: String) {
    companion object {
        fun parsePasswordPolicy(line: String): PasswordPolicy {
            val regex = """(\d+)-(\d+) (\w): (\w+)""".toRegex()
            val matchResult = regex.find(line)
            val (x, y, char, password) = matchResult!!.destructured
            return PasswordPolicy(x.toInt(), y.toInt(), char.first(), password)
        }
    }
}

class OccurrenceValidator : (PasswordPolicy) -> Boolean {
    override fun invoke(passwordPolicy: PasswordPolicy): Boolean {
        val occurrenceRange = passwordPolicy.x..passwordPolicy.y
        return passwordPolicy.password.count { it == passwordPolicy.char } in occurrenceRange
    }
}

class PositionalValidator : (PasswordPolicy) -> Boolean {
    override fun invoke(passwordPolicy: PasswordPolicy): Boolean {
        val xIndex = toIndex(passwordPolicy.x)
        val yIndex = toIndex(passwordPolicy.y)
        return (passwordPolicy.password[xIndex] == passwordPolicy.char) xor
                (passwordPolicy.password[yIndex] == passwordPolicy.char)

    }

    private fun toIndex(position: Int): Int = position - 1
}
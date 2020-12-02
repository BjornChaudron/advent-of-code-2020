package nl.quintor.aoc.day2

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day2", "input.txt")
    val passwordValidator = PasswordValidator(path)
    val nrOfValidPasswords = passwordValidator.findNrOfValidPasswords()
    println("Amount of valid passwords: $nrOfValidPasswords")
}

class PasswordValidator(private val path: Path, private val fileReader: FileReader = FileReader()) {
    fun findNrOfValidPasswords(): Int {
        val lines = fileReader.readFile(path)
        val passwordPolicies = lines.map { line -> PasswordPolicy.parsePasswordPolicy(line) }

        return passwordPolicies.count { isValidPassword(it) }
    }

    private fun isValidPassword(passwordPolicy: PasswordPolicy): Boolean {
        return passwordPolicy.password.count { it == passwordPolicy.char } in passwordPolicy.range
    }
}

class PasswordPolicy(val range: IntRange, val char: Char, val password: String) {
    companion object {
        fun parsePasswordPolicy(line: String): PasswordPolicy {
            val regex = """(\d+)-(\d+) (\w): (\w+)""".toRegex()
            val matchResult = regex.find(line)
            val (rangeStart, rangeEnd, char, password) = matchResult!!.destructured
            return PasswordPolicy(rangeStart.toInt()..rangeEnd.toInt(), char.first(), password)
        }
    }
}


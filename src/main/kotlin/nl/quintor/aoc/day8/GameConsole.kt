package nl.quintor.aoc.day8

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day8", "input.txt")
    val gameConsole = GameConsole(path)

    gameConsole.bootWithDefaultBootSequence()
    println("Boot sequence completed. Accumulator: ${gameConsole.acc}")
}

class GameConsole(private val path: Path, private val fileReader: FileReader = FileReader()) {
    var acc = 0
        private set

    fun bootWithDefaultBootSequence(): Boolean {
        val bootSequence = loadBootSequence()
        return boot(bootSequence)
    }

    fun boot(bootSequence: List<Instruction>): Boolean {
        var procedure = 0
        val processed = mutableSetOf<Int>()

        while (procedure < bootSequence.size) {
            if (processed.contains(procedure)) return false

            processed.add(procedure)

            val (op, arg) = bootSequence[procedure]

            when (op) {
                "acc" -> acc += arg
                "jmp" -> procedure += arg -1
            }

            procedure++
        }

        return true
    }

    private fun loadBootSequence(): List<Instruction> {
        return fileReader.readAllLines(path)
            .map { it.split(" ") }
            .map { (op, arg) -> Instruction(op, arg.toInt()) }
    }
}

data class Instruction(val operation: String, val argument: Int)
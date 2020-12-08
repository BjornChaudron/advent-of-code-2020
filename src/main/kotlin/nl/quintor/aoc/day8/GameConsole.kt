package nl.quintor.aoc.day8

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day8", "input.txt")
    val gameConsole = GameConsole(path)

    gameConsole.bootWithDefaultBootSequence()
    println("Boot sequence completed. Accumulator: ${gameConsole.acc}")

    gameConsole.reset()

    println("Booting console in safe mode ..")
    gameConsole.repairBootSequence()
    println("Boot sequence repaired. Accumulator: ${gameConsole.acc}")
}

class GameConsole(private val path: Path, private val fileReader: FileReader = FileReader()) {
    var acc = 0
        private set

    fun bootWithDefaultBootSequence(): Boolean {
        val bootSequence = loadBootSequence()
        return boot(bootSequence)
    }

    private fun boot(bootSequence: List<Instruction>): Boolean {
        var procedure = 0
        val processed = mutableSetOf<Int>()

        while (procedure < bootSequence.size) {
            if (processed.contains(procedure)) return false

            processed.add(procedure)

            val (op, arg) = bootSequence[procedure]

            when (op) {
                "acc" -> acc += arg
                "jmp" -> procedure += arg - 1
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

    fun reset() {
        acc = 0
    }

    fun repairBootSequence(): Boolean {
        val bootSequences = generateBootSequences()
        bootSequences.forEach { bootSequence ->
            val isSuccessful = boot(bootSequence)

            if (isSuccessful) return true
            reset()
        }

        return false
    }

    private fun generateBootSequences(): Sequence<List<Instruction>> {
        val defaultBootSequence = loadBootSequence()
        val bootSequences = mutableListOf<List<Instruction>>()
        for (i in defaultBootSequence.indices) {
            val (op, arg) = defaultBootSequence[i]
            if (op in listOf("nop", "jmp")) {
                val newOp = if (op == "nop") "jmp" else "nop"
                defaultBootSequence.toMutableList()
                    .apply { this[i] = Instruction(newOp, arg) }
                    .also { bootSequences.add(it) }
            }
        }

        return bootSequences.asSequence()
    }
}

data class Instruction(val operation: String, val argument: Int)
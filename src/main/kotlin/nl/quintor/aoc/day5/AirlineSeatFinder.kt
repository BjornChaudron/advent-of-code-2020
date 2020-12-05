package nl.quintor.aoc.day5

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day5", "input.txt")
    val airlineSeatFinder = AirlineSeatFinder(path)
    val highestSeatId = airlineSeatFinder.findHighestSeatId()
    println("Highest seatId is $highestSeatId")
}

class AirlineSeatFinder(private val path: Path, private val fileReader: FileReader = FileReader()) {
    object SeatGrid {
        val rows = 0..127
        val columns = 0..7
    }

    fun findHighestSeatId(): Int {
        val seatInstructions = fileReader.readFile(path)

        return seatInstructions
            .map { instruction -> getSeat(instruction) }
            .map { seat -> seat.seatId }
            .maxOrNull()!!
    }

    private fun getSeat(instruction: String): Seat {
        val (rowInstruction, columnInstruction) = parseBinaryInstruction(instruction)
        val row = findSeatRow(rowInstruction)
        val column = findSeatColumn(columnInstruction)
        val seatId = getSeatId(row, column)

        return Seat(row, column, seatId)
    }

    fun parseBinaryInstruction(seatInstruction: String): Pair<String, String> {
        val rowInstruction = seatInstruction.take(7)
        val columnInstruction = seatInstruction.takeLast(3)

        return Pair(rowInstruction, columnInstruction)
    }

    fun findSeatRow(instruction: String): Int {
        val normalizedInstruction = normalizeRowInstruction(instruction)
        return followInstruction(normalizedInstruction, SeatGrid.rows)
    }

    private fun normalizeRowInstruction(instruction: String): String {
        return instruction.toUpperCase()
            .replace("F", "L")
            .replace("B", "H")
    }

    private fun followInstruction(instruction: String, range: IntRange): Int {
        var rangeSelection = range

        instruction.forEach {
            val middleIndex = rangeSelection.last - (rangeSelection.count() / 2 - 1)
            when (it) {
                'L' -> rangeSelection = rangeSelection.first until middleIndex
                'H' -> rangeSelection = middleIndex..rangeSelection.last
            }
        }

        return rangeSelection.first
    }

    fun findSeatColumn(instruction: String): Int {
        val normalizedInstruction = normalizeColumnInstruction(instruction)
        return followInstruction(normalizedInstruction, SeatGrid.columns)
    }

    private fun normalizeColumnInstruction(instruction: String): String {
        return instruction.toUpperCase()
            .replace("R", "H")
    }

    fun getSeatId(row: Int, column: Int): Int = row * 8 + column
}

data class Seat(val row: Int, val column: Int, val seatId: Int)
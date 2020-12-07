package nl.quintor.aoc.day5

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

typealias MappingTable = Map<String, String>

val mappingTable = mapOf(
    "F" to "0",
    "B" to "1",
    "L" to "0",
    "R" to "1"
)

fun main() {
    val path = Paths.get("src", "main", "resources", "day5", "input.txt")
    val airlineSeatFinder = AirlineSeatFinder(path)
    val seats = airlineSeatFinder.getSeats();
    val highestSeatId = airlineSeatFinder.findHighestSeatId(seats)
    println("Highest seatId is $highestSeatId\n")

    val mySeatId = airlineSeatFinder.findMySeat(seats)
    println("My seatId is $mySeatId")
}

class AirlineSeatFinder(private val path: Path, private val fileReader: FileReader = FileReader()) {
    object SeatGrid {
        val rows = 0..127
        val columns = 0..7
    }

    fun findHighestSeatId(seats: List<Seat>): Int {
        return seats
            .map { seat -> seat.seatId }
            .maxOrNull()!!
    }

    fun getSeats(): List<Seat> {
        return fileReader.readAllLines(path)
            .map { instruction -> getSeat(instruction) }
            .sortedBy { seat -> seat.seatId }
    }

    private fun getSeat(instruction: String): Seat {
        val binaryInstruction = normalizeToBinary(instruction, mappingTable)
        val (rowInstruction, columnInstruction) = splitRowAndColumnData(binaryInstruction)

        val row = parseBinaryInstruction(rowInstruction)
        val column = parseBinaryInstruction(columnInstruction)
        val seatId = parseBinaryInstruction(binaryInstruction)

        return Seat(row, column, seatId)
    }

    private fun normalizeToBinary(instruction: String, mappingTable: MappingTable): String {
        var binaryInstruction = instruction

        mappingTable.forEach {
            binaryInstruction = binaryInstruction.replace(it.key, it.value)
        }

        return binaryInstruction
    }

    fun splitRowAndColumnData(seatInstruction: String): Pair<String, String> {
        val rowInstruction = seatInstruction.take(7)
        val columnInstruction = seatInstruction.takeLast(3)

        return Pair(rowInstruction, columnInstruction)
    }

    private fun parseBinaryInstruction(instruction: String): Int {
        return instruction.toInt(2)
    }

    fun findMySeat(seats: List<Seat>): Int {
        val occupiedSeatIds = seats.map { it.seatId }
        val allSeatIds = occupiedSeatIds.first()..occupiedSeatIds.last()

        return allSeatIds.first { it !in occupiedSeatIds }
    }
}

data class Seat(val row: Int, val column: Int, val seatId: Int)
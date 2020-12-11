package nl.quintor.aoc.day11

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    val path = Paths.get("src", "main", "resources", "day11", "input.txt")
    val seatModeler = SeatModeler(path)
    val nrOfOccupiedSeats = seatModeler.getOccupiedSeats()
    println("$nrOfOccupiedSeats seats occupied after several rounds")
}

class SeatModeler(private val path: Path, private val fileReader: FileReader = FileReader()) {

    private val seatsIndex = mutableMapOf<UUID, List<Seat>>()

    fun getOccupiedSeats(): Int {
        val seats = readSeats()
        cacheAdjacentSeats(seats)

        var previouslyOccupiedSeats = 0
        var currentlyOccupiedSeats = 0

        do {
            previouslyOccupiedSeats = currentlyOccupiedSeats
            val seatsToBeOccupied = mutableListOf<Seat>()
            val seatsToBeEmptied = mutableListOf<Seat>()

            seats.forEach { seat ->
                val occupiedAdjacentSeats = seatsIndex[seat.id]!!.count { it.isOccupied }

                when {
                    occupiedAdjacentSeats == 0 -> seatsToBeOccupied.add(seat)
                    occupiedAdjacentSeats >= 4 -> seatsToBeEmptied.add(seat)
                }
            }

            seatsToBeOccupied.forEach { it.isOccupied = true }
            seatsToBeEmptied.forEach { it.isOccupied = false }

            seatsToBeOccupied.clear()
            seatsToBeEmptied.clear()

            currentlyOccupiedSeats = seats.count { it.isOccupied }

        } while (previouslyOccupiedSeats != currentlyOccupiedSeats)

        return previouslyOccupiedSeats
    }

    private fun readSeats(): List<Seat> {
        return fileReader.readAllLines(path)
            .mapIndexed { rowIndex, row ->
                row.mapIndexed { columnIndex, seat ->
                    if (seat != '.')
                        Seat(UUID.randomUUID(), rowIndex, columnIndex)
                    else
                        null
                }.filterNotNull()
            }
            .flatMap { it.toList() }
    }

    private fun cacheAdjacentSeats(seats: List<Seat>) {
        seats.forEach { seat ->
            val adjacentSeats = getAdjacentSeats(seats, seat)
            seatsIndex[seat.id] = adjacentSeats
        }
    }

    private fun getAdjacentSeats(seats: List<Seat>, seat: Seat): List<Seat> {
        return seats
            .filter { it.row in seat.row - 1..seat.row + 1 }
            .filter { it.column in seat.column - 1..seat.column + 1 }
            .filter { it != seat }
    }

}

data class Seat(val id: UUID, val row: Int, val column: Int, var isOccupied: Boolean = false)
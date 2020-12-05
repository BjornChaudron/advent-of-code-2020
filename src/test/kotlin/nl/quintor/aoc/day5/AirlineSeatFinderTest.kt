package nl.quintor.aoc.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class AirlineSeatFinderTest {
    private val path = Paths.get("src", "test", "resources", "day5", "test-input.txt")
    lateinit var airlineSeatFinder: AirlineSeatFinder

    @BeforeEach
    fun setup() {
        airlineSeatFinder = AirlineSeatFinder(path)
    }

    @Test
    fun `FBFBBFFRLR should split row and column instruction correctly`() {
        val instruction = "FBFBBFFRLR"

        val (rowInstruction, columnInstruction) = airlineSeatFinder.parseBinaryInstruction(instruction);

        assertThat(rowInstruction).isEqualTo("FBFBBFF")
        assertThat(columnInstruction).isEqualTo("RLR")
    }

    @Test
    fun `FBFBBFFRLR should return row 44`() {
        val rowInstruction = "FBFBBFF"

        val row = airlineSeatFinder.findSeatRow(rowInstruction)

        assertThat(row).isEqualTo(44)
    }

    @Test
    fun `FBFBBFFRLR should return column 5`() {
        val columnInstruction = "RLR"

        val column = airlineSeatFinder.findSeatColumn(columnInstruction)

        assertThat(column).isEqualTo(5)
    }

    @Test
    fun `FBFBBFFRLR should return sead ID 357`() {
        val row = 44
        val column = 5

        val seatId = airlineSeatFinder.getSeatId(row, column)

        assertThat(seatId).isEqualTo(357)
    }
}
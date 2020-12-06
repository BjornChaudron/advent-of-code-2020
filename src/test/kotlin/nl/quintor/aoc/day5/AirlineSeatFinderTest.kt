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

        val (rowInstruction, columnInstruction) = airlineSeatFinder.splitRowAndColumnData(instruction);

        assertThat(rowInstruction).isEqualTo("FBFBBFF")
        assertThat(columnInstruction).isEqualTo("RLR")
    }
}
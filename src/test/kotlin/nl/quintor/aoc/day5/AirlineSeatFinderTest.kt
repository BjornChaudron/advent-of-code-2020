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
    fun `highest seatId should be 820`() {
        val seats = airlineSeatFinder.getSeats()

        val highestSeatId = airlineSeatFinder.findHighestSeatId(seats)

        assertThat(highestSeatId).isEqualTo(820)
    }

    @Test
    fun `my seatId should be 120`() {
        val seats = airlineSeatFinder.getSeats()

        val mySeatId = airlineSeatFinder.findMySeat(seats)

        assertThat(mySeatId).isEqualTo(120)
    }
}
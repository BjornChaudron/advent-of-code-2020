package nl.quintor.aoc.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class SeatModelerTest {
    private val path = Paths.get("src", "test", "resources", "day11", "test-input.txt")
    private lateinit var seatModeler: SeatModeler

    @BeforeEach
    fun setup() {
        seatModeler = SeatModeler(path)
    }

    @Test
    fun `test dataset should have 37 seats occupied after stabilisation`() {
        val occupiedSeats = seatModeler.getOccupiedSeats()

        assertThat(occupiedSeats).isEqualTo(37)
    }

    @Test
    fun test() {
        val rounds = listOf(
            "#.##.##.##\n" +
                    "#######.##\n" +
                    "#.#.#..#..\n" +
                    "####.##.##\n" +
                    "#.##.##.##\n" +
                    "#.#####.##\n" +
                    "..#.#.....\n" +
                    "##########\n" +
                    "#.######.#\n" +
                    "#.#####.##",
            "#.LL.L#.##\n" +
                    "#LLLLLL.L#\n" +
                    "L.L.L..L..\n" +
                    "#LLL.LL.L#\n" +
                    "#.LL.LL.LL\n" +
                    "#.LLLL#.##\n" +
                    "..L.L.....\n" +
                    "#LLLLLLLL#\n" +
                    "#.LLLLLL.L\n" +
                    "#.#LLLL.##",
            "#.##.L#.##\n" +
                    "#L###LL.L#\n" +
                    "L.#.#..#..\n" +
                    "#L##.##.L#\n" +
                    "#.##.LL.LL\n" +
                    "#.###L#.##\n" +
                    "..#.#.....\n" +
                    "#L######L#\n" +
                    "#.LL###L.L\n" +
                    "#.#L###.##",
            "#.#L.L#.##\n" +
                    "#LLL#LL.L#\n" +
                    "L.L.L..#..\n" +
                    "#LLL.##.L#\n" +
                    "#.LL.LL.LL\n" +
                    "#.LL#L#.##\n" +
                    "..L.L.....\n" +
                    "#L#LLLL#L#\n" +
                    "#.LLLLLL.L\n" +
                    "#.#L#L#.##",
            "#.#L.L#.##\n" +
                    "#LLL#LL.L#\n" +
                    "L.#.L..#..\n" +
                    "#L##.##.L#\n" +
                    "#.#L.LL.LL\n" +
                    "#.#L#L#.##\n" +
                    "..L.L.....\n" +
                    "#L#L##L#L#\n" +
                    "#.LLLLLL.L\n" +
                    "#.#L#L#.##")

        rounds.map { round -> round.count { it == '#' } }.forEach { println(it) }
    }
}
package nl.quintor.aoc.day3;

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class TobogganTrajectoryTest {

    private val path = Paths.get("src", "test", "resources", "day3", "test-input.txt")
    lateinit var tobogganTrajectory: TobogganTrajectory

    @BeforeEach
    fun setup() {
        tobogganTrajectory = TobogganTrajectory(path)
    }

    @Test
    fun `should encounter 7 trees on small dataset`() {
        val nrOfTrees = tobogganTrajectory.findNrOfTrees()

        assertThat(nrOfTrees).isEqualTo(3)
    }
}

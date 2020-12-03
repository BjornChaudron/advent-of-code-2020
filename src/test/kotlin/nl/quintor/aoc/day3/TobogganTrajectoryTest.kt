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
        val slope = Slope(3, 1)
        val nrOfTrees = tobogganTrajectory.findNrOfTrees(slope)

        assertThat(nrOfTrees).isEqualTo(7)
    }

    @Test
    fun `should get product of 336 after multiplying amount of trees per slope`() {
        val product = tobogganTrajectory.getProductOfTreesPerSlope()

        assertThat(product).isEqualTo(336)
    }
}

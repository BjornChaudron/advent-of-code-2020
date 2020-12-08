package nl.quintor.aoc.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Path
import java.nio.file.Paths

class GameConsoleTest {
    private val path: Path = Paths.get("src", "test", "resources", "day8", "test-input.txt")
    lateinit var gameConsole: GameConsole

    @BeforeEach
    fun setup() {
        gameConsole = GameConsole(path)
    }

    @Test
    fun `should accumulate to 5 using default boot instructions`() {
        gameConsole.bootWithDefaultBootSequence()

        val acc = gameConsole.acc
        assertThat(acc).isEqualTo(5)
    }

    @Test
    fun `should accumulate to 8 using boot sequence repair`() {
        gameConsole.repairBootSequence()

        val acc = gameConsole.acc
        assertThat(acc).isEqualTo(8)
    }
}
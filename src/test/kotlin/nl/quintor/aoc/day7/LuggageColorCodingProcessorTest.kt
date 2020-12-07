package nl.quintor.aoc.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class LuggageColorCodingProcessorTest {
    private val path = Paths.get("src", "test", "resources", "day7", "test-input.txt")
    lateinit var luggageColorCodingProcessor: LuggageColorCodingProcessor

    @BeforeEach
    fun setup() {
        luggageColorCodingProcessor = LuggageColorCodingProcessor(path)
    }

    @Test
    fun `sum of unique answers by group should be 11`() {
        val nrOfBagColoursContainingGold = luggageColorCodingProcessor.getNrOfBagsContainingGoldBag()

        assertThat(nrOfBagColoursContainingGold).isEqualTo(4)
    }
}
package nl.quintor.aoc.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class CustomsDeclarationsParserTest {
    private val path = Paths.get("src", "test", "resources", "day6", "test-input.txt")
    lateinit var customsDeclarationsParser: CustomsDeclarationsParser

    @BeforeEach
    fun setup() {
        customsDeclarationsParser = CustomsDeclarationsParser(path)
    }

    @Test
    fun `sum of unique answers by group should be 11`() {
        val sumOfDistinctAnswers = customsDeclarationsParser.getSumOfDistinctGroupAnswers()

        assertThat(sumOfDistinctAnswers).isEqualTo(11)
    }

    @Test
    fun `sum of common answers by should be 6`() {
        val sumOfDistinctAnswers = customsDeclarationsParser.getSumOfCommonAnswers()

        assertThat(sumOfDistinctAnswers).isEqualTo(6)
    }
}
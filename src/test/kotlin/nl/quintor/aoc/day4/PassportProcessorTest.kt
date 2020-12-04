package nl.quintor.aoc.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class PassportProcessorTest {
    private val path = Paths.get("src", "test", "resources", "day4", "test-input.txt")
    lateinit var passportProcessor: PassportProcessor

    @BeforeEach
    fun setup() {
        passportProcessor = PassportProcessor(path)
    }

    @Test
    fun `test dataset should contain 2 valid passports`() {
        val validPassportEntries = passportProcessor.getPasswordsWithAllRequiredFields()

        assertThat(validPassportEntries.size).isEqualTo(2)
    }
}
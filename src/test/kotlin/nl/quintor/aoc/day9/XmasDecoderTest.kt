package nl.quintor.aoc.day9

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class XmasDecoderTest {
    private val path = Paths.get("src", "test", "resources", "day9", "test-input.txt")
    private lateinit var xmasDecoder: XmasDecoder

    @BeforeEach
    fun setup() {
        xmasDecoder = XmasDecoder(path)
    }

    @Test
    fun `test with preamble of five should return 127`() {
        xmasDecoder.maxPreambleSize = 5

        val sequenceBreaker = xmasDecoder.findSequenceBreaker()

        assertThat(sequenceBreaker).isEqualTo(127)
    }

    @Test
    fun `test should find encryption weakness of 62`() {
        xmasDecoder.maxPreambleSize = 5

        val encryptionWeakness = xmasDecoder.findEncryptionWeakness()

        assertThat(encryptionWeakness).isEqualTo(62)
    }
}
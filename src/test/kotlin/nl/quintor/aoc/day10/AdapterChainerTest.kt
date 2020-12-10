package nl.quintor.aoc.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class AdapterChainerTest {

    private val path = Paths.get("src", "test", "resources", "day10", "test-input.txt")
    private lateinit var adapterChainer: AdapterChainer

    @Test
    fun `small dataset should contain 7 differences of 1`() {
        adapterChainer = AdapterChainer(path)
        val distribution = adapterChainer.getDistribution()

        assertThat(distribution.singles).isEqualTo(7)
    }

    @Test
    fun `small dataset should contain 5 differences of 3`() {
        adapterChainer = AdapterChainer(path)
        val distribution = adapterChainer.getDistribution()

        assertThat(distribution.triples).isEqualTo(5)
    }
}
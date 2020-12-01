package nl.quintor.aoc.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class ReportRepairTest {
    private val path = Paths.get("src", "test", "resources", "day1", "test-input.txt")
    private lateinit var reportRepairer: ReportRepairer

    @BeforeEach
    fun setup() {
        val reportRepairer = ReportRepairer(path)
    }

    @Test
    fun `test pair with small dataset`() {
        val product = reportRepairer.getProductOfPair()

        assertThat(product).isEqualTo(514579)
    }

    @Test
    fun `test triple with small dataset`() {
        val product = reportRepairer.getProductOfTriple()

        assertThat(product).isEqualTo(241861950)
    }
}
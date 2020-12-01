package nl.quintor.aoc.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class ReportRepairTest {

    @Test
    fun `test pair with small dataset`() {
        val path = Paths.get("src", "test", "resources", "day1", "test-input.txt")
        val reportRepairer = ReportRepairer(path)

        val product = reportRepairer.getProductOfPair()

        assertThat(product).isEqualTo(514579)
    }

    @Test
    fun `test triple with small dataset`() {
        val path = Paths.get("src", "test", "resources", "day1", "test-input.txt")
        val reportRepairer = ReportRepairer(path)

        val product = reportRepairer.getProductOfTriple()

        assertThat(product).isEqualTo(241861950)
    }
}
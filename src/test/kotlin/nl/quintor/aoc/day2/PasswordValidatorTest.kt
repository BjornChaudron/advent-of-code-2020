package nl.quintor.aoc.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class PasswordValidatorTest {

    private val path = Paths.get("src", "test", "resources", "day2", "test-input.txt")
    lateinit var passwordValidator: PasswordValidator

    @BeforeEach
    fun setup() {
        passwordValidator = PasswordValidator(path)
    }

    @Test
    fun `small dataset should contain two valid passwords`() {
        val nrOfValidPasswords = passwordValidator.findNrOfValidPasswords()

        assertThat(nrOfValidPasswords).isEqualTo(2)
    }
}
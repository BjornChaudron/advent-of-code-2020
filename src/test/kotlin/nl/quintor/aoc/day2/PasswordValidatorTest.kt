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
    fun `small dataset should contain two valid passwords using occurrence mode`() {
        val validationMode = ValidationMode.OCCURRENCE

        val nrOfValidPasswords = passwordValidator.findNrOfValidPasswords(validationMode)

        assertThat(nrOfValidPasswords).isEqualTo(2)
    }

    @Test
    fun `small dataset should contain one valid password using positional mode`() {
        val validationMode = ValidationMode.POSITIONAL

        val nrOfValidPasswords = passwordValidator.findNrOfValidPasswords(validationMode)

        assertThat(nrOfValidPasswords).isEqualTo(1)
    }
}
package nl.quintor.aoc.day3

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day3", "input.txt")
    val tobogganTrajectory = TobogganTrajectory(path)
    val slopePart1 = slopes[1]
    val nrOfTreesPart1 = tobogganTrajectory.findNrOfTrees(slopePart1)

    println("$nrOfTreesPart1 trees encountered!")

    println("\nUsing multiple slopes:")

    val product = tobogganTrajectory.getProductOfTreesPerSlope()
    println("The product of all encountered trees is $product")
}

class TobogganTrajectory(private val path: Path, private val fileReader: FileReader = FileReader()) {
    fun findNrOfTrees(slope: Slope): Long {
        val lines = fileReader.readFile(path)
        var horizontalPosition = 0
        var nrOfTrees: Long = 0

        for (index in lines.indices step slope.verticalSteps) {
            val line = lines[index]

            if (horizontalPosition > line.lastIndex) {
                horizontalPosition -= line.length
            }

            val square = line[horizontalPosition]

            if (square.isTree()) {
                nrOfTrees++
            }

            horizontalPosition += slope.horizontalSteps
        }

        return nrOfTrees
    }

    fun getProductOfTreesPerSlope(): Long {
        return slopes
                .map { slope -> findNrOfTrees(slope) }
                .reduce { acc, next -> acc * next }
    }
}

data class Slope(val horizontalSteps: Int, val verticalSteps: Int)

val slopes = listOf(
        Slope(1, 1),
        Slope(3, 1),
        Slope(5, 1),
        Slope(7, 1),
        Slope(1, 2)
)

fun Char.isTree(): Boolean = this == '#'
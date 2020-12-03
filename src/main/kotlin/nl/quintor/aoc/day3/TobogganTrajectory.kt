package nl.quintor.aoc.day3

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day3", "input.txt")
    val tobogganTrajectory = TobogganTrajectory(path)
    val nrOfTrees = tobogganTrajectory.findNrOfTrees()

    println("$nrOfTrees trees encountered!")
}

class TobogganTrajectory(private val path: Path, private val fileReader: FileReader = FileReader()) {
    fun findNrOfTrees(): Int {
        val lines = fileReader.readFile(path)
        val stepSize = 3
        var horizontalPosition = 0
        var nrOfTrees = 0

        lines.forEach { line ->
            println(horizontalPosition)

            if (horizontalPosition > line.lastIndex) {
                horizontalPosition -= line.length
            }

            val square = line[horizontalPosition]

            if (square.isTree()) {
                nrOfTrees++
            }

            horizontalPosition += stepSize
        }

        return nrOfTrees
    }
}

fun Char.isTree(): Boolean = this == '#'
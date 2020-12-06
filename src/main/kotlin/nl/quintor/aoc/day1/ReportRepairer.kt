package nl.quintor.aoc.day1

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day1", "input.txt")
    val reportRepairer = ReportRepairer(path)
    val productOfPair = reportRepairer.getProductOfPair()
    println("Product of pair: \n $productOfPair")

    // Part 2
    println()
    val productOfTriple = reportRepairer.getProductOfTriple()
    println("Product of triple: \n $productOfTriple")
}

class ReportRepairer(private val filePath: Path) {
    private val fileReader = FileReader()

    fun getProductOfPair(): Int {
        val lines = fileReader.readAllLines(filePath)
        val numbers = parseNumbers(lines)

        val pair = findPair(numbers)

        return multiply(pair.first, pair.second)
    }

    private fun parseNumbers(lines: List<String>) = lines.map { it.toInt() }.toSet()

    private fun findPair(numbers: Set<Int>): Pair<Int, Int> {
        val pair = Pair(0, 0)

        numbers.forEach { x ->
            val y = 2020 - x

            if (y in numbers) {
                return Pair(x, y)
            }
        }

        return pair
    }

    private fun multiply(vararg numbers: Int) = numbers.reduce { acc, next -> acc * next}

    // Part 2
    fun getProductOfTriple(): Int {
        val lines = fileReader.readAllLines(filePath)
        val numbers = parseNumbers(lines)

        val pair = findTriple(numbers)

        return multiply(pair.first, pair.second, pair.third)
    }

    private fun findTriple(numbers: Set<Int>): Triple<Int, Int, Int> {
        var triple = Triple(0, 0, 0)

        numbers.forEach { x ->
            numbers.forEach { y ->
                numbers.forEach { z ->
                    if (x + y + z == 2020) {
                        triple = Triple(x, y, z)
                    }
                }
            }
        }

        return triple
    }
}
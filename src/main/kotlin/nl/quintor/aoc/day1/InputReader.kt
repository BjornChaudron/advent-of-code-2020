package nl.quintor.aoc.day1

import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

class InputReader {
    fun getInput(): Path {
        val scanner = Scanner(System.`in`)
        return Paths.get(scanner.next())
    }
}
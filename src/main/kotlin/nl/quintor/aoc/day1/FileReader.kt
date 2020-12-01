package nl.quintor.aoc.day1

import java.nio.file.Files
import java.nio.file.Path

class FileReader {
    fun readFile(path: Path): List<String> {
        return Files.readAllLines(path)
    }
}
package nl.quintor.aoc.core

import java.nio.file.Files
import java.nio.file.Path

class FileReader {
    fun readFile(path: Path): List<String> {
        return Files.readAllLines(path)
    }
}
package nl.quintor.aoc.core

import java.nio.file.Files
import java.nio.file.Path

class FileReader {
    fun readAllLines(path: Path): List<String> {
        return Files.readAllLines(path)
    }

    fun readFile(path: Path): String {
        return Files.readString(path)
    }
}
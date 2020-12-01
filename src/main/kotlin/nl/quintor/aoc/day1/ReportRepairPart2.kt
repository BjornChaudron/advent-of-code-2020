package nl.quintor.aoc.day1

fun main() {
    val inputReader = InputReader()
    val filePath = inputReader.getInput()

    val fileReader = FileReader()
    val entries = fileReader
            .readFile(filePath)
            .map { it.toInt() }

    var triple = Triple(0, 0, 0)

    entries.forEach { x ->
        entries.forEach { y ->
            entries.forEach { z ->
                if (x + y + z == 2020) {
                    triple = Triple(x, y, z)
                }
            }
        }
    }

    val product = triple.first * triple.second * triple.third

    println(product)
}

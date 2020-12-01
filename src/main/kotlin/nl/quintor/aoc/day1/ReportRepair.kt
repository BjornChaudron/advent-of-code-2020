package nl.quintor.aoc.day1

fun main() {
    val inputReader = InputReader()
    val filePath = inputReader.getInput()

    val fileReader = FileReader()
    val entries = fileReader
            .readFile(filePath)
            .map { it.toInt() }

    var pair = Pair(0, 0)

    entries.forEach { x ->
        entries.forEach { y ->
            if (x + y == 2020) {
                pair = Pair(x, y)
            }
        }
    }

    val product = pair.first * pair.second

    println(product)
}

package nl.quintor.aoc.day6

import nl.quintor.aoc.core.FileReader
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val path = Paths.get("src", "main", "resources", "day6", "input.txt")
    val customsDeclarationsParser = CustomsDeclarationsParser(path)

    val sumOfDistinctGroupAnswers = customsDeclarationsParser.getSumOfDistinctGroupAnswers()
    println("The sum of unique answers per group is: $sumOfDistinctGroupAnswers\n")

    val sumOfCommonGroupAnswers = customsDeclarationsParser.getSumOfCommonAnswers()
    print("The sum of common answers per group is: $sumOfCommonGroupAnswers")
}

class CustomsDeclarationsParser(private val path: Path, private val fileReader: FileReader = FileReader()) {
    val questions = 'a'..'z'

    fun getSumOfDistinctGroupAnswers(): Int {
        val content = fileReader.readFile(path)
        val answersByGroup = groupAnswersByGroup(content)

        return answersByGroup
            .map { groupAnswers ->
                questions.filter { it in groupAnswers }.count()
            }.sum()
    }

    fun getSumOfCommonAnswers(): Int {
        val content = fileReader.readFile(path)
        val answersByGroup = groupAnswersByGroup(content)

        return answersByGroup
            .map { group -> getCommonAnswersByGroup(group) }
            .sum()
    }

    private fun getCommonAnswersByGroup(groupAnswers: String): Int {
        val answersPerGroupMember = groupAnswers.split("\n")

        return questions
            .filter { question ->
                answersPerGroupMember.all { answer -> question in answer }
            }.count()
    }

    private fun groupAnswersByGroup(allAnswers: String): List<String> = allAnswers.split("\n\n")
}
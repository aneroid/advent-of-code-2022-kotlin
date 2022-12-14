enum class RockPaperScissors(val theirLetter: String, val myLetter: String, val choiceScore: Int) {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3),
    ;
    
    private fun beats(other: RockPaperScissors) =
        when (this) {
            ROCK -> SCISSORS
            PAPER -> ROCK
            SCISSORS -> PAPER
        } == other
    
    fun scoreVersus(other: RockPaperScissors) =
        when {
            beats(other) -> Goal.WIN
            this == other -> Goal.DRAW
            else -> Goal.LOSE
        }.moveScore
    
    companion object {
        fun fromString(letter: String) =
            values().first { it.theirLetter == letter || it.myLetter == letter }
    }
}

enum class Goal(val letter: String, val moveScore: Int) {
    LOSE("X", 0),
    DRAW("Y", 3),
    WIN("Z", 6),
    ;
    
    fun toRPS(theirs: RockPaperScissors) =
        RockPaperScissors.values().first { mine -> mine.scoreVersus(theirs) == moveScore }
    
    companion object {
        fun fromRPS(rps: RockPaperScissors) = values().first { it.letter == rps.myLetter }
    }
}

class Day02(input: List<String>) {
    private val data = parseInput(input)
    
    private fun List<Pair<RockPaperScissors, RockPaperScissors>>.calculateScore() =
        fold(0) { acc, (theirs, mine) ->
            acc + mine.scoreVersus(theirs) + mine.choiceScore
        }
    
    fun partOne(): Int =
        data.calculateScore()
    
    fun partTwo(): Int =
        data.map { (theirs, mine) -> theirs to Goal.fromRPS(mine).toRPS(theirs) }
            .calculateScore()
    
    private companion object {
        fun parseInput(input: List<String>) =
            input.map { line ->
                line.split(" ")
                    .map(RockPaperScissors::fromString)
            }.map { it.first() to it.last() }
    }
}

fun main() {
    val testInput = """
        A Y
        B X
        C Z
    """.trimIndent().lines()
    check(Day02(testInput).partOne() == 15)
    check(Day02(testInput).partTwo() == 12)  // uncomment when ready
    
    val input = readInput("Day02")
    println("partOne: ${Day02(input).partOne()}\n")
    println("partTwo: ${Day02(input).partTwo()}\n")
}

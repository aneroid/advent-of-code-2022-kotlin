enum class RockPaperScissors(val theirLetter: String, val myLetter: String, val value: Int) {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3),
    ;
    
    fun beats(other: RockPaperScissors) = when (this) {
        ROCK -> other == SCISSORS
        PAPER -> other == ROCK
        SCISSORS -> other == PAPER
    }
    
    fun moveScore(other: RockPaperScissors) =
        when {
            beats(other) -> 6
            this == other -> 3
            else -> 0
        }
    
    companion object {
        fun fromString(letter: String) = values().first { it.theirLetter == letter || it.myLetter == letter }
    }
}

enum class Goal(val letter: String, val value: Int) {
    LOSE("X", 0),
    DRAW("Y", 3),
    WIN("Z", 6),
    ;
    
    fun toRPS(theirs: RockPaperScissors) =
        RockPaperScissors.values().first { mine ->
            when (this) {
                LOSE -> theirs.beats(mine)
                DRAW -> mine == theirs
                WIN -> mine.beats(theirs)
            }
        }
    
    companion object {
        fun fromRPS(rps: RockPaperScissors) = values().first { it.letter == rps.myLetter }
    }
}

class Day02(input: List<String>) {
    private val data = parseInput(input)
    
    private fun List<Pair<RockPaperScissors, RockPaperScissors>>.calculateScore() =
        fold(0) { acc, (theirs, mine) ->
            acc + mine.moveScore(theirs) + mine.value
        }
    
    fun partOne(): Int =
        data.calculateScore()
    
    fun partTwo(): Int {
        val newData = data.map { (theirs, mine) ->
            theirs to Goal.fromRPS(mine)
        }
        val newMoves = newData.map { (theirs, goal) ->
            theirs to goal.toRPS(theirs)
        }
        return newMoves.calculateScore()
    }
    
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

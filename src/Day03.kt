class Day03(private val input: List<String>) {
    private val data
        get() = parseInput(input)
    
    private val priorities = (('a'..'z') + ('A'..'Z')).zip(1..52).toMap()
    
    fun partOne(): Int =
        data
            .asSequence()
            .map { it.first.toSet().intersect(it.second.toSet()).first() }
            .sumOf { priorities.getValue(it) }
    
    fun partTwo(): Int =
        input.chunked(3)
            .map { chunks ->
                chunks.map(String::toSet)
                    .reduce { acc, set -> acc.intersect(set) }
                    .first()
            }.sumOf { priorities.getValue(it) }
    
    private companion object {
        fun parseInput(input: List<String>) =
            input.map {
                it.length.let { len ->
                    it.substring(0, len / 2) to it.substring(len / 2)
                }
            }
    }
}

fun main() {
    val testInput = readInput("Day03_test")
    check(Day03(testInput).partOne() == 157)
    check(Day03(testInput).partTwo() == 70)  // uncomment when ready
    
    val input = readInput("Day03")
    println("partOne: ${Day03(input).partOne()}\n")
    println("partTwo: ${Day03(input).partTwo()}\n")
}

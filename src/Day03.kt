class Day03(private val input: List<String>) {
    private val data = parseInput(input)
    private val priorities = ('a'..'z').zip(1..26).toMap() +
            ('A'..'Z').zip(27..52).toMap()
    
    fun partOne(): Int {
        return data.asSequence()
            .map { it.first.toSet().intersect(it.second.toSet()).first() }
            .map { priorities.getValue(it) }
            .sum()
        
    }
    
    fun partTwo(): Int =
        input.chunked(3)
            .map { ls ->
                ls.map(String::toSet)
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
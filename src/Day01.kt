class Day01(input: List<String>) {
    private val data = parseInput(input)
    
    fun partOne() =
        data.maxOf { it.sum() }
    
    fun partTwo() =
        data.map { it.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    
    private companion object {
        fun parseInput(input: List<String>): List<List<Int>> =
            buildList {
                var group = mutableListOf<Int>()
                for (line in input) {
                    if (line.isBlank()) {
                        add(group)
                        group = mutableListOf()
                    } else {
                        group.add(line.toInt())
                    }
                }
                add(group)
            }
    }
}

fun main() {
    val testInput = readInput("Day01_test")
    check(Day01(testInput).partOne() == 24000)
    check(Day01(testInput).partTwo() == 45000)  // uncomment when ready
    
    val input = readInput("Day01")
    println("partOne: ${Day01(input).partOne()}\n")
    println("partTwo: ${Day01(input).partTwo()}\n")
}

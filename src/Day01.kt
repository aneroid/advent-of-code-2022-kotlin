class Day01(input: String) {
    private val data = parseInput(input)
    
    fun partOne() =
        data.maxOf { it.sum() }
    
    fun partTwo()=
        data.map { it.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    
    private companion object {
        fun parseInput(input: String): List<List<Int>> =
            input.split("\r\n\r\n", "\n\n")
                .map { group ->
                    group.split("\r\n", "\n")
                        .filter { it.isNotBlank() }
                        .map { it.toInt() }
                }
    }
}

fun main() {
    val testInput = readInputAsString("Day01_test")
    check(Day01(testInput).partOne() == 24000)
    check(Day01(testInput).partTwo() == 45000)  // uncomment when ready
    
    val input = readInputAsString("Day01")
    println("partOne: ${Day01(input).partOne()}\n")
    println("partTwo: ${Day01(input).partTwo()}\n")
}

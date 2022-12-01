class Day01(input: List<String>) {
    private val data = parseInput(input)

    fun partOne(): Int {
        return 0
    }

    fun partTwo(): Int {
        return 0
    }

    private companion object {
        fun parseInput(input: List<String>) = input
    }
}

fun main() {
    val testInput = readInput("Day01_test")
    check(Day01(testInput).partOne() == 1)
    // check(Day01(testInput).partTwo() == 1)  // uncomment when ready

    val input = readInput("Day01")
    println("partOne: ${Day01(input).partOne()}\n")
    println("partTwo: ${Day01(input).partTwo()}\n")
}

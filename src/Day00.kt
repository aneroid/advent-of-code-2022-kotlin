class Day00(private val input: List<String>) {
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
    val testInput = readInput("Day00_test")
    check(Day00(testInput).partOne() == 1)
    // check(Day00(testInput).partTwo() == 1)  // uncomment when ready
    
    val input = readInput("Day00")
    println("partOne: ${Day00(input).partOne()}\n")
    println("partTwo: ${Day00(input).partTwo()}\n")
}

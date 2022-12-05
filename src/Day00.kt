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
    val input = readInput("Day00")
    
    println("part One:")
    assertThat(Day00(testInput).partOne()).isEqualTo(1)
    println("actual: ${Day00(input).partOne()}\n")
    
    println("part Two:")
    // assertThat(Day00(testInput).partTwo()).isEqualTo(1)  // uncomment when ready
    // println("actual: ${Day00(input).partTwo()}\n")  // uncomment when ready
}

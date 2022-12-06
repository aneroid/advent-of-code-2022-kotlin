class Day06(private val input: String) {
    private val data = parseInput(input)
    
    private fun String.indexForUniqSeqLen(size: Int) =
        asSequence()
            .windowed(size) { it.toSet().size }
            .indexOfFirst { it == size }
            .plus(size)
    
    fun partOne(): Int = input.indexForUniqSeqLen(4)
    
    fun partTwo(): Int = input.indexForUniqSeqLen(14)
    
    private companion object {
        fun parseInput(input: String) = input
    }
}

fun main() {
    val testInputs = listOf(
        // testString to (partOneResult to partTwoResult)
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to (7 to 19),
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to (5 to 23),
        "nppdvjthqldpwncqszvftbrmjlhg" to (6 to 23),
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to (10 to 29),
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to (11 to 26),
    )
    val input = readInput("Day06").first()
    
    println("part One:")
    testInputs.forEach { testInput ->
        assertThat(Day06(testInput.first).partOne()).isEqualTo(testInput.second.first)
    }
    println("actual: ${Day06(input).partOne()}\n")
    
    println("part Two:")
    testInputs.forEach { testInput ->
        assertThat(Day06(testInput.first).partTwo()).isEqualTo(testInput.second.second)
    }
    println("actual: ${Day06(input).partTwo()}\n")  // uncomment when ready
}

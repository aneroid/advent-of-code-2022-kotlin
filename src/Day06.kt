class Day06(private val input: String) {
    private val data = parseInput(input)
    
    fun partOne(): Int {
        return input.withIndex()
            .windowed(4)
            .first { chars ->
                chars.map { it ->
                    it.value
                }.toSet().size == 4
            }.last().index + 1
    }
    
    fun partTwo(): Int {
        return 0
    }
    
    private companion object {
        fun parseInput(input: String) = input
    }
}

fun main() {
    val testInputs = listOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 7,
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
        "nppdvjthqldpwncqszvftbrmjlhg" to 6,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11,
    )
    val input = readInput("Day06").first()
    
    println("part One:")
    testInputs.forEach { testInput ->
        assertThat(Day06(testInput.first).partOne()).isEqualTo(testInput.second)
    }
    println("actual: ${Day06(input).partOne()}\n")
    
    println("part Two:")
    // assertThat(Day06(testInput).partTwo()).isEqualTo(1)  // uncomment when ready
    // println("actual: ${Day06(input).partTwo()}\n")  // uncomment when ready
}

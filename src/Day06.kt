class Day06(private val input: String) {
    private val data = parseInput(input)
    
    fun partOne(): Int =
        input.withIndex()
            .windowed(4)
            .first { chars ->
                chars.map { it ->
                    it.value
                }.toSet().size == 4
            }.last().index + 1
    
    fun partTwo(): Int =
        input.withIndex()
            .windowed(14)
            .first { chars ->
                chars.map { it ->
                    it.value
                }.toSet().size == 14
            }.last().index + 1
    
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
    val testInputsPart2 = listOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
        "nppdvjthqldpwncqszvftbrmjlhg" to 23,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 29,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 26,
    )
    testInputsPart2.forEach { testInput ->
        assertThat(Day06(testInput.first).partTwo()).isEqualTo(testInput.second)
    }
    println("actual: ${Day06(input).partTwo()}\n")  // uncomment when ready
}

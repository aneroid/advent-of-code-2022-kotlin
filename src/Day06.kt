class Day06(private val input: String) {
    private val data = parseInput(input)
    
    private fun String.indexForUniqSeqLen(size: Int) =
        withIndex()
        .windowed(size)
        .first { chars ->
            chars.map { it.value }.toSet().size == size
        }.last().index + 1
    
    fun partOne(): Int = input.indexForUniqSeqLen(4)
    
    fun partTwo(): Int = input.indexForUniqSeqLen(14)
    
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

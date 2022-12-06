class Day06(private val input: String) {
    private fun String.indexForUniqSeqLen(size: Int) =
        asSequence()
            .windowed(size) { it.toSet().size }
            .indexOfFirst { it == size }
            .plus(size)
    
    fun partOne(): Int = input.indexForUniqSeqLen(4)
    
    fun partTwo(): Int = input.indexForUniqSeqLen(14)
}

fun main() {
    val testInputs = readInput("Day06_test")
    val input = readInput("Day06").first()
    
    println("part One:")
    assertThat(testInputs.map { Day06(it).partOne()}).isEqualTo(listOf(7, 5, 6, 10, 11))
    println("actual: ${Day06(input).partOne()}\n")
    
    println("part Two:")
    assertThat(testInputs.map { Day06(it).partTwo()}).isEqualTo(listOf(19, 23, 23, 29, 26))
    println("actual: ${Day06(input).partTwo()}\n")  // uncomment when ready
}

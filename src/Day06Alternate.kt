class Day06Alternate(private val input: String) {
    private fun String.indexForUniqSeqLen(size: Int): Int {
        val checkArray = IntArray(26) { 0 }
        return withIndex().first { (index, char) ->
            checkArray[char - 'a']++
            if (index >= size) {
                checkArray[elementAt(index - size) - 'a']--
            }
            index >= size - 1 && checkArray.none { it > 1 }
        }.index + 1
    }
    
    fun partOne(): Int = input.indexForUniqSeqLen(4)
    
    fun partTwo(): Int = input.indexForUniqSeqLen(14)
}

fun main() {
    val testInputs = readInput("Day06_test")
    val input = readInput("Day06").first()
    
    println("part One:")
    assertThat(testInputs.map { Day06Alternate(it).partOne() }).isEqualTo(listOf(7, 5, 6, 10, 11))
    println("actual: ${Day06Alternate(input).partOne()}\n")
    
    println("part Two:")
    assertThat(testInputs.map { Day06Alternate(it).partTwo() }).isEqualTo(listOf(19, 23, 23, 29, 26))
    println("actual: ${Day06Alternate(input).partTwo()}\n")
}

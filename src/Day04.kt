class Day04(input: List<String>) {
    private val data = parseInput(input)
    
    fun partOne() =
        data
            .filter { (assign1, assign2) -> assign1 contains assign2 || assign2 contains assign1 }
            .size
    
    fun partTwo(): Int =
        data
            .filter { (assign1, assign2) -> assign1 overlapsWith assign2 || assign2 overlapsWith assign1 }
            .size
    
    private companion object {
        fun parseInput(input: List<String>) =
            input.map { line ->
                val (range1, range2) = line.split(",").map { it.toRange() }
                range1 to range2
            }
        
        private fun String.toRange() =
            substringBefore("-").toInt()..substringAfter("-").toInt()
    }
    
    private infix fun IntRange.contains(other: IntRange): Boolean =
        first <= other.first && last >= other.last
    
    private infix fun IntRange.overlapsWith(other: IntRange): Boolean =
        other.first in this || other.last in this
}

fun main() {
    val testInput = readInput("Day04_test")
    check(Day04(testInput).partOne() == 2)
    check(Day04(testInput).partTwo() == 4)  // uncomment when ready
    
    val input = readInput("Day04")
    println("partOne: ${Day04(input).partOne()}\n")
    println("partTwo: ${Day04(input).partTwo()}\n")  // uncomment when ready
}

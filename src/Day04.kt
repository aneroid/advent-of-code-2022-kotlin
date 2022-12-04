class Day04(private val input: List<String>) {
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
                line.split(",")
                    .map { it.split("-").map(String::toInt) }
                    .map { it.first() to it.last() }
            }
    }
}

private infix fun Pair<Int, Int>.contains(other: Pair<Int, Int>): Boolean =
    first <= other.first && second >= other.second

private infix fun Pair<Int, Int>.overlapsWith(other: Pair<Int, Int>): Boolean =
    other.first in first..second || other.second in first..second

fun main() {
    val testInput = readInput("Day04_test")
    check(Day04(testInput).partOne() == 2)
    check(Day04(testInput).partTwo() == 4)  // uncomment when ready
    
    val input = readInput("Day04")
    println("partOne: ${Day04(input).partOne()}\n")
    println("partTwo: ${Day04(input).partTwo()}\n")  // uncomment when ready
}

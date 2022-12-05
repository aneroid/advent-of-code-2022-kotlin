data class Move(val howMany: Int, val fromStack: Int, val toStack: Int)

class Day05(input: String) {
    private val data = parseInput(input)
    private val stacks = getStacks(data.first.dropLast(1).reversed())
    private val moves = data.second.mapNotNull(::extractMove)
    
    private fun extractMove(line: String) =
        line.takeIf(String::isNotBlank)?.run {
            Move(
                line.substringAfter(" ").substringBefore(" ").toInt(),
                line.substringAfter("from ").substringBefore(" ").toInt() - 1,
                line.substringAfter("to ").toInt() - 1,
            )
        }
    
    private fun getStacks(stacksText: List<String>): MutableList<MutableList<Char>> {
        val stacks = MutableList(stacksText.first().length.plus(1) / 4) { mutableListOf<Char>() }
        stacksText.flatMap(::extractStackLine)
            .onEach { (idx, char) ->
                stacks[idx].add(char)
            }
        println("stacks start: $stacks")
        return stacks
    }
    
    private fun extractStackLine(line: String) =
        line
            .withIndex()
            .filter { it.value in 'A'..'Z' }
            .map { (it.index - 1) / 4 to it.value }
    
    private fun List<List<Char>>.allTops(): List<Char> {
        return map { it.last() }.also { println("final stacks: ${it.joinToString("")}") }
    }
    
    private fun MutableList<MutableList<Char>>.doMove(move: Move, withReversal: Boolean = false) {
        this[move.toStack].addAll(this[move.fromStack].takeLast(move.howMany).withReversal(withReversal))
        repeat(move.howMany) {
            this[move.fromStack].removeLast()
        }
    }
    
    private fun <T> Iterable<T>.withReversal(reverse: Boolean): Iterable<T> =
        if (reverse) reversed() else this.toList()  // consistently a copy
    
    
    fun partOne(): List<Char> {
        moves.forEach { move -> stacks.doMove(move, true) }
        return stacks.allTops()
    }
    
    fun partTwo(): List<Char> {
        moves.forEach { move -> stacks.doMove(move) }
        return stacks.allTops()
    }
    
    private fun parseInput(input: String): Pair<List<String>, List<String>> {
        val (stacksText, movesText) = input.split("\n\n", "\r\n\r\n").map(String::lines)
        return stacksText to movesText
    }
}

fun main() {
    val testInput = readInputAsString("Day05_test")
    val input = readInputAsString("Day05")
    
    // part One
    check(Day05(testInput).partOne() == "CMZ".toList())
    println("partOne: ${Day05(input).partOne()}\n")
    
    // part Two
    check(Day05(testInput).partTwo() == "MCD".toList())  // uncomment when ready
    println("partTwo: ${Day05(input).partTwo()}\n")  // uncomment when ready
}

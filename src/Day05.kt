private data class Move(val howMany: Int, val fromStack: Int, val toStack: Int)

class Day05(input: List<List<String>>) {
    private val stacks = getStacks(input.first().dropLast(1).reversed())
    private val moves = input.last().mapNotNull(::extractMove)
    
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
    
    private fun extractMove(line: String) =
        line.takeIf(String::isNotBlank)?.run {
            Move(
                line.substringAfter(" ").substringBefore(" ").toInt(),
                line.substringAfter("from ").substringBefore(" ").toInt() - 1,
                line.substringAfter("to ").toInt() - 1,
            )
        }
    
    private fun List<List<Char>>.allTops(): List<Char> {
        return map { it.last() }.also { println("final stacks: ${it.joinToString("")}") }
    }
    
    private fun MutableList<MutableList<Char>>.doMove(move: Move, withReversal: Boolean = false) {
        this[move.toStack].addAll(this[move.fromStack].takeLast(move.howMany).withReversal(withReversal))
        repeat(move.howMany) {
            this[move.fromStack].removeLast()
        }
    }
    
    private fun <T> Iterable<T>.withReversal(reverse: Boolean) =
        if (reverse) reversed() else this.toList()  // consistently a copy
    
    
    fun partOne(): List<Char> {
        moves.forEach { move -> stacks.doMove(move, true) }
        return stacks.allTops()
    }
    
    fun partTwo(): List<Char> {
        moves.forEach { move -> stacks.doMove(move) }
        return stacks.allTops()
    }
}

fun main() {
    val testInput = readInputBlocks("Day05_test")
    val input = readInputBlocks("Day05")
    
    println("part One:")
    assertThat(Day05(testInput).partOne()).isEqualTo("CMZ".toList())
    println("actual: ${Day05(input).partOne()}\n")
    
    println("part Two:")
    assertThat(Day05(testInput).partTwo()).isEqualTo("MCD".toList())  // uncomment when ready
    println("actual: ${Day05(input).partTwo()}\n")  // uncomment when ready
    
}

class Day11(input: List<String>) {
    private val monkeys: List<Monkey> = parseInput(input)
    
    private fun printMonkeyItems(round: Int = 0) {
        if (round != 0) {
            println("After round $round:")
        }
        monkeys.forEachIndexed { index, monkey ->
            println("Monkey $index: ${monkey.itemLevels}")
        }
        println()
    }
    
    private fun doRound() {
        monkeys.forEachIndexed { index, monkey ->
            monkey.see().map { monkey.`do`(it) }
        }
    }
    
    private fun Monkey.see() =
        buildList {
            while (itemLevels.isNotEmpty()) {
                inspected++
                itemLevels.removeFirst().let(changeLevel).div(3).also(::add)
            }
        }
    
    private fun Monkey.`do`(level: Int) {
        val throwTo = if (level % testDivBy == 0) throwTrue else throwFalse
        monkeys[throwTo].itemLevels.addLast(level)
    }
    
    fun partOne(): Int {
        printMonkeyItems()
        repeat(20) {
            doRound()
            // printMonkeyItems(it + 1)
        }
        printMonkeyItems()
        return monkeys.map(Monkey::inspected).sortedDescending().take(2).reduce(Int::times)
    }
    
    fun partTwo(): Int {
        return 0
    }
    
    private companion object {
        class Monkey(
                val itemLevels: ArrayDeque<Int>,
                val changeLevel: (Int) -> Int,
                val testDivBy: Int,
                val throwTrue: Int,
                val throwFalse: Int,
                var inspected: Int = 0,
        ) {
        }
        
        fun parseInput(input: List<String>) =
            input.chunked(7).map { lines ->
                Monkey(
                    lines[1].substringAfter("items: ").split(", ").map(String::toInt).let(::ArrayDeque),
                    lines[2].substringAfter("= ").toOperation(),
                    lines[3].substringAfter("by ").toInt(),
                    lines[4].substringAfter("monkey ").toInt(),
                    lines[5].substringAfter("monkey ").toInt(),
                )
            }
        
        fun String.toOperation(): (Int) -> Int {
            val arg1 = substringBefore(" ").toIntOrNull()
            val arg2 = substringAfterLast(" ").toIntOrNull()
            val operator: (Int, Int) -> Int = when (substringAfter(" ").substringBefore(" ")) {
                "+" -> Int::plus
                "-" -> Int::minus
                "*" -> Int::times
                "/" -> Int::div
                else -> throw IllegalArgumentException("Unknown operation: $this")
            }
            return { old: Int -> operator(arg1 ?: old, arg2 ?: old) }
        }
    }
}

fun main() {
    val testInput = readInput("Day11_test")
    val input = readInput("Day11")
    
    println("part One:")
    assertThat(Day11(testInput).partOne()).isEqualTo(10605)
    println("actual: ${Day11(input).partOne()}\n")
    
    println("part Two:")
    // assertThat(Day11(testInput).partTwo()).isEqualTo(1)
    // println("actual: ${Day11(input).partTwo()}\n")
}

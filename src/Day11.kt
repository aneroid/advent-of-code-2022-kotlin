class Day11(input: List<String>) {
    private var worryReducer = { it: Long -> it / 3 }
    private val monkeys: List<Monkey> = parseInput(input)
    
    private fun printMonkeyItems(round: Int = 0) {
        if (round != 0) {
            println("After round $round:")
        }
        monkeys.forEachIndexed { index, monkey ->
            println("Monkey $index: ${monkey.itemLevels} | ${monkey.inspected}")
        }
        println()
    }
    
    private fun doRound() {
        monkeys.forEach { monkey ->
            monkey.see().map { monkey.`do`(it) }
        }
    }
    
    private fun Monkey.see() =
        buildList {
            while (itemLevels.isNotEmpty()) {
                inspected++
                itemLevels.removeFirst().let(changeLevel).let(worryReducer).also(::add)
            }
        }
    
    private fun Monkey.`do`(level: Long) {
        val throwTo = if (level % testDivBy == 0L) throwTrue else throwFalse
        monkeys[throwTo].itemLevels.addLast(level)
    }
    
    private fun solve(repetitions: Int): Long {
        printMonkeyItems()
        repeat(repetitions) {
            doRound()
            // printMonkeyItems(it + 1)
        }
        printMonkeyItems()
        return monkeys.map(Monkey::inspected).sortedDescending().take(2).map(Int::toLong).reduce(Long::times)
    }
    
    fun partOne(): Long = solve(20)
    
    fun partTwo(repetitions: Int): Long {
        val factor = monkeys.map(Monkey::testDivBy).also { println(it) }.reduce(Int::times)
        println("Factor: $factor")
        worryReducer = { it % factor }
        return solve(repetitions)
    }
    
    private companion object {
        class Monkey(
                val itemLevels: ArrayDeque<Long>,
                val changeLevel: (Long) -> Long,
                val testDivBy: Int,
                val throwTrue: Int,
                val throwFalse: Int,
                var inspected: Int = 0,
        )
        
        fun parseInput(input: List<String>) =
            input.chunked(7).map { lines ->
                Monkey(
                    lines[1].substringAfter("items: ").split(", ").map(String::toLong).let(::ArrayDeque),
                    lines[2].substringAfter("= ").toOperation(),
                    lines[3].substringAfter("by ").toInt(),
                    lines[4].substringAfter("monkey ").toInt(),
                    lines[5].substringAfter("monkey ").toInt(),
                )
            }
        
        fun String.toOperation(): (Long) -> Long {
            val arg1 = substringBefore(" ").toLongOrNull()
            val arg2 = substringAfterLast(" ").toLongOrNull()
            val operator: (Long, Long) -> Long = when (substringAfter(" ").substringBefore(" ")) {
                "+" -> Long::plus
                "-" -> Long::minus
                "*" -> Long::times
                "/" -> Long::div
                else -> throw IllegalArgumentException("Unknown operation: $this")
            }
            return { old: Long -> operator(arg1 ?: old, arg2 ?: old) }
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
    assertThat(Day11(testInput).partTwo(20)).isEqualTo(99 * 103)
    assertThat(Day11(testInput).partTwo(1_000)).isEqualTo(5204 * 5192)
    assertThat(Day11(testInput).partTwo(2_000)).isEqualTo(10419 * 10391)
    assertThat(Day11(testInput).partTwo(10_000)).isEqualTo(52166 * 52013L)
    println("actual: ${Day11(input).partTwo(10_000)}\n")
}

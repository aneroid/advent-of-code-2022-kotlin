import Operation.*

private enum class Operation(val cycleCost: Int) {
    NOOP(1),
    ADDX(2),
    ;
    
    companion object {
        fun fromString(s: String): Operation =
            values().first { it.name == s.uppercase() }
    }
}

private class CPU(var accX: Int = 1) {
    private var cycle = 1
    private val cyclesOfInterest = 20..220 step 40
    val signalStrengths = mutableListOf<Int>()
    
    private fun Int.isCyclesOfInterest() = (this - 20) % 40 == 0
    
    private fun processCycle() {
        if (cycle in cyclesOfInterest) {
            // println("    *** processCycle: $cycle")
            signalStrengths += signalStrength()
        }
    }
    
    private fun signalStrength(): Int = accX * cycle
    
    fun execute(op: Operation, arg: Int) {
        repeat(op.cycleCost) {
            // println("  cycle $cycle: accX = $accX")
            processCycle()
            cycle++
        }
        when (op) {
            NOOP -> Unit
            ADDX -> accX += arg
        }
    }
}

class Day10(input: List<String>) {
    private val program = input.map {
        it.substring(0, 4).toOP() to it.substringAfter(" ", "0").toInt()
    }
    
    private fun String.toOP() = Operation.fromString(this)
    
    fun partOne(): Int {
        val cpu = CPU()
        program.forEach { (instr, arg) ->
            // println("${instr.name} $arg")
            cpu.execute(instr, arg)
        }
        println("signal strengths: ${cpu.signalStrengths}")
        return cpu.signalStrengths.sum()
    }
    
    fun partTwo(): Int {
        return 0
    }
    
}

fun main() {
    val testInput = readInput("Day10_test")
    val input = readInput("Day10")
    
    println("part One:")
    assertThat(Day10(testInput).partOne()).isEqualTo(13140)
    println("actual: ${Day10(input).partOne()}\n")
    
    println("part Two:")
    // uncomment when ready
    // assertThat(Day10(testInput).partTwo()).isEqualTo(1)
    // println("actual: ${Day10(input).partTwo()}\n")
}

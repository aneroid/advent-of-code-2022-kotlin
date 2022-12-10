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
    
    // variables added for part 2
    val pixels = mutableListOf(mutableListOf<Char>())
    val spriteSize = 3
    
    private fun processCycle() {
        if (cycle in cyclesOfInterest) {
            // println("    *** processCycle: $cycle")
            signalStrengths += signalStrength()
        }
        // drawSprite
        val crtPos = (cycle - 1) % 40
        val spritePos = accX - 1
        pixels.last().add(if (crtPos in spritePos until spritePos + spriteSize) '#' else '.')
        if (cycle % 40 == 0) {
            pixels.add(mutableListOf())
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
    
    private fun processProgram(): CPU =
        CPU().apply {
            program.forEach { (instr, arg) ->
                // println("${instr.name} $arg")
                execute(instr, arg)
            }
        }
    
    fun partOne(): Int {
        val cpu = processProgram()
        println("signal strengths: ${cpu.signalStrengths}")
        return cpu.signalStrengths.sum()
    }
    
    fun partTwo(): List<String> {
        val cpu = processProgram()
        println(cpu.pixels.joinToString("\n") {
            it.joinToString("")
                // .replace(".", " ")
                // .replace("#", "█")
                .replace(".", "⚫️️")
                .replace("#", "⚪")
        })
        return cpu.pixels.map { it.joinToString("") }
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
    val expectedOutputPart2 = """
        ##..##..##..##..##..##..##..##..##..##..
        ###...###...###...###...###...###...###.
        ####....####....####....####....####....
        #####.....#####.....#####.....#####.....
        ######......######......######......####
        #######.......#######.......#######.....
    """.trimIndent().lines()
    assertThat(Day10(testInput).partTwo().take(6)).isEqualTo(expectedOutputPart2)
    println("actual: ${Day10(input).partTwo()}\n")
}

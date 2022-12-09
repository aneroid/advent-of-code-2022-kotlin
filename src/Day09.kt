import kotlin.math.absoluteValue
import kotlin.math.sign

private data class Point(val x: Int, val y: Int) {
    constructor(strCoords: String) : this(
        x = strCoords.split(',').first().toInt(),
        y = strCoords.split(',').last().toInt()
    )
    
    override fun toString() = "P($x, $y)"
}

class Day09(private val input: List<String>) {
    private val steps = parseInput(input)
    
    private fun Pair<Int, Int>.toPoint() = Point(first, second)
    
    private fun Point.move(dir: Char, dist: Int = 1) =
        when (dir) {
            'R' -> x + dist to y
            'L' -> x - dist to y
            'U' -> x to y - dist
            'D' -> x to y + dist
            else -> throw IllegalArgumentException("Unknown direction: $dir")
        }.toPoint()
    
    private fun Point.follow(head: Point): Point =
        when {
            head == this -> this
            head.x == x && (head.y - y).absoluteValue > 1 -> Point(x, y + (head.y - y).sign)
            head.y == y && (head.x - x).absoluteValue > 1 -> Point(x + (head.x - x).sign, y)
            (head.x - x).absoluteValue > 1 || (head.y - y).absoluteValue > 1 ->
                Point(x + (head.x - x).sign, y + (head.y - y).sign)
            
            else -> this
        }
    
    fun partOne(): Int =
        buildSet {
            steps.fold(Point(0, 0) to Point(0, 0)) { (stepHead, stepTail), step ->
                generateSequence(stepHead to stepTail) { (head, tail) ->
                    head.move(step.first)
                        .let { it to tail.follow(it) }
                        .also { add(it.second) }
                }.drop(1).take(step.second).last()
            }
        }.size
    
    fun partTwo(): Int {
        return 0
    }
    
    private companion object {
        fun parseInput(input: List<String>) =
            input.map { it.substringBefore(' ').first() to it.substringAfter(' ').toInt() }
    }
}


fun main() {
    val testInput = readInput("Day09_test")
    val input = readInput("Day09")
    
    println("part One:")
    assertThat(Day09(testInput).partOne()).isEqualTo(13)
    println("actual: ${Day09(input).partOne()}\n")
    
    println("part Two:")
    // uncomment when ready
    // assertThat(Day09(testInput).partTwo()).isEqualTo(1)
    // println("actual: ${Day09(input).partTwo()}\n")
}

class Day08(private val input: List<String>) {
    private val forest = parseInput(input)
    
    private fun isVisible(x: Int, y: Int, height: Int): Boolean =
        requiredIndices(x, y)
            .any { pairs ->
                pairs.all { (cx, cy) -> forest[cy][cx] < height }
            }
    
    private fun scenicScore(x: Int, y: Int, height: Int): Int =
        requiredIndices(x, y)
            .map { pairs ->
                val treesInPath = pairs.map { (cx, cy) -> forest[cy][cx] }
                val shorter = treesInPath.takeWhile { it < height }
                shorter.size + if (shorter.indices.last < treesInPath.indices.last) 1 else 0
            }.reduce { acc, i -> acc * i }
    
    private fun requiredIndices(x: Int, y: Int): Sequence<List<Pair<Int, Int>>> {
        return sequenceOf(
            (0 until x).reversed().map { it to y },
            (x + 1 until forest[0].size).map { it to y },
            (0 until y).reversed().map { x to it },
            (y + 1 until forest.size).map { x to it },
        )
    }
    
    private fun treesVisibleAtEdge(): Int =
        forest.size * 2 + forest[0].size * 2 - 4
    
    private fun isAtEdge(x: Int, y: Int) =
        x == 0 || y == 0 || x == forest[0].indices.last || y == forest.indices.last
    
    
    fun partOne(): Int =
        treesVisibleAtEdge() + forest.flatMapIndexed { y: Int, rows: List<Int> ->
            rows.mapIndexed { x: Int, height: Int ->
                !isAtEdge(x, y) && isVisible(x, y, height)
            }
        }.count { it }
    
    fun partTwo(): Int =
        forest.flatMapIndexed { y: Int, rows: List<Int> ->
            rows.mapIndexed { x: Int, height: Int ->
                if (isAtEdge(x, y)) 0 else scenicScore(x, y, height)
            }
        }.max()
    
    private companion object {
        fun parseInput(input: List<String>) = input
            .map { line -> line.map { char -> char.toString().toInt() } }
    }
}

fun main() {
    val testInput = readInput("Day08_test")
    val input = readInput("Day08")
    
    println("part One:")
    assertThat(Day08(testInput).partOne()).isEqualTo(21)
    println("actual: ${Day08(input).partOne()}\n")
    
    println("part Two:")
    // uncomment when ready
    assertThat(Day08(testInput).partTwo()).isEqualTo(8)
    println("actual: ${Day08(input).partTwo()}\n")
}

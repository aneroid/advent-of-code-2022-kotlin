class Day07(input: List<String>) {
    private val allPaths = parseInput(input)
    
    private fun parseInput(input: List<String>): Map<String, Int> =
        buildMap {
            var index = 0
            var currDir = ""
            while (index < input.size) {
                val line = input[index++]
                when (line.take(4)) {
                    "$ cd" -> currDir = changedDir(currDir, line.drop(5))
                    "$ ls" -> index += listDir(input.drop(index))
                        .map { this[changedDir(currDir, it.second)] = it.first }.size
                    
                    else -> throw IllegalArgumentException("Invalid command: $line")
                }
            }
        }
    
    private fun listDir(input: List<String>): List<Pair<Int, String>> =
        input
            .takeWhile { !it.startsWith("$") }
            .map { (it.substringBefore(" ").toIntOrNull() ?: 0) to it.substringAfter(" ") }
    
    private fun changedDir(currDir: String, dirName: String): String =
        when (dirName) {
            "/" -> "/"
            ".." -> currDir.substringBeforeLast("/")
            else -> if (currDir != "/") "$currDir/$dirName" else "/$dirName"
        }
    
    fun totalSizeOfDir(dirName: String): Int {
        return allPaths
            .filterKeys { it.startsWith("$dirName/") || dirName == "/" }
            .values
            .sum()
    }
    
    fun partOne(): Int =
        allPaths
            .map { totalSizeOfDir(it.key) }
            .filter { it <= 100_000 }
            .sum()
    
    fun partTwo(): Int {
        val total = 70_000_000
        val required = 30_000_000
        val unused = total - totalSizeOfDir("/")
        return allPaths
            .map { totalSizeOfDir(it.key) }
            .filter { it + unused >= required }
            .min()
    }
}

fun main() {
    val testInput = readInput("Day07_test")
    val input = readInput("Day07")
    
    println("part One:")
    val day07Test = Day07(testInput)
    assertThat(
        listOf("/a/e", "/a", "/d", "/").map { day07Test.totalSizeOfDir(it) }
    ).isEqualTo(listOf(584, 94853, 24933642, 48381165))
    assertThat(day07Test.partOne()).isEqualTo(95437)
    println("actual: ${Day07(input).partOne()}\n")
    
    println("part Two:")
    assertThat(Day07(testInput).partTwo()).isEqualTo(24933642)  // uncomment when ready
    println("actual: ${Day07(input).partTwo()}\n")  // uncomment when ready
}

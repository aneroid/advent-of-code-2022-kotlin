private typealias Directory = MutableList<Pair<Int, String>>
private fun Directory.size(): Int = sumOf { it.first }

class Day07(input: List<String>) {
    private val allFiles = parseInput(input)
    
    private fun parseInput(input: List<String>): Map<List<String>, Directory> =
        buildMap {
            var index = 0
            var currDir = listOf<String>()
            while (index < input.size) {
                val line = input[index++]
                when (line.take(4)) {
                    "$ cd" -> {
                        currDir = changeDir(currDir, line.drop(5))
                        getOrPut(currDir) { mutableListOf() }
                    }
                    
                    "$ ls" -> {
                        index += listDir(this, input, index, currDir)
                    }
                    
                    else -> throw IllegalArgumentException("Invalid command: $line")
                }
            }
        }
    
    private fun listDir(
            allFiles: MutableMap<List<String>, Directory>,
            input: List<String>,
            index: Int,
            currDir: List<String>,
    ): Int =
        input
            .drop(index)
            .takeWhile { !it.startsWith("$") }
            .map { line ->
                val (size, name) = line.split(" ")
                if (size == "dir") {
                    allFiles.getOrPut(currDir + name) { mutableListOf() }
                } else {
                    allFiles.getValue(currDir).add(size.toInt() to name)
                }
            }.size
    
    private fun changeDir(currDir: List<String>, dirName: String): List<String> =
        when (dirName) {
            "/" -> listOf("/")
            ".." -> currDir.dropLast(1)
            else -> currDir + dirName
        }
    
    fun totalSizeOfDir(dirName: String): Int {
        val dirKey = listOf("/") + (dirName.drop(1).takeIf { it.isNotEmpty() }?.split("/") ?: listOf())
        return totalSizeOfDir(dirKey)
    }
    
    private fun totalSizeOfDir(dirKey: List<String>) =
        allFiles
            .filterKeys { it.take(dirKey.size) == dirKey }
            .values
            .sumOf { it.size() }
    
    fun partOne(): Int =
        allFiles
            .map { totalSizeOfDir(it.key) }
            .filter { it <= 100_000 }
            .sum()
    
    fun partTwo(): Int {
        val total = 70_000_000
        val required = 30_000_000
        val unused = total - totalSizeOfDir("/")
        return allFiles
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

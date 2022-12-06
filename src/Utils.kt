import java.io.File
import java.math.BigInteger
import java.security.MessageDigest


private fun file(name: String) = File(
    "..\\_inputs",
    "$name.txt"
)

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = file(name).readLines()

/**
 * Reads input as String txt file.
 */
fun readInputAsString(name: String) = file(name).readText()

fun readInputBlocks(name: String) =
    file(name)
        .readText()
        .split("\n\n", "\r\n\r\n")
        .map(String::lines)

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Barebones implementation of Google Truth's `assertThat()` and `isEqualTo()`.
 */
class Checkable<T>(private val result: T) {
    fun isEqualTo(expected: T) =
        check(result == expected) { """
            
            expected: $expected
            but was : $result
        """.trimIndent()
        }
}

fun <T> assertThat(someResult: T): Checkable<T> = Checkable(someResult)

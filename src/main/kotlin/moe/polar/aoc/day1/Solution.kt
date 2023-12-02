package moe.polar.aoc.day1

import moe.polar.aoc.DaySolution

class Solution : DaySolution() {
    private val text = input(1)

    private val digitRegex = Regex("""(?=(?<number>one|two|three|four|five|six|seven|eight|nine|zero|\d))""")

    private fun String.textToDigit(): Int {
        if (this.length == 1)
            return this.toInt()

        return when (this) {
            "zero" -> 0
            "one" -> 1
            "two" -> 2
            "three" -> 3
            "four" -> 4
            "five" -> 5
            "six" -> 6
            "seven" -> 7
            "eight" -> 8
            "nine" -> 9
            else -> error("Failed to convert $this to digit")
        }
    }

    override fun part1() {
        var sum = 0

        for (line in text.lines()) {
            if (line.isEmpty() || line.isBlank())
                continue

            val first = line.first { it.isDigit() }
            val second = line.last { it.isDigit() }
            sum += "$first$second".toInt()
        }

        println(sum)
    }

    override fun part2() {
        var sum = 0

        for (line in text.lines()) {
            val matches = digitRegex.findAll(line)

            val first = matches.first().groupValues.last()
            val second = matches.last().groupValues.last()

            sum += "${first.textToDigit()}${second.textToDigit()}".toInt()
        }

        println(sum)
    }

}

fun main() {
    val s = Solution()
    s.part1()
    s.part2()
}
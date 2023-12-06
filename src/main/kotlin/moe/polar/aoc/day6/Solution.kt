package moe.polar.aoc.day6

import moe.polar.aoc.DaySolution

class Solution : DaySolution() {
    private val text = input(6)
    private val lines = text.lines()

    private fun String.toPart1(): List<Long> =
        this.split("""\s+""".toRegex()).filterIndexed { index, _ -> index != 0 }.map { it.toLong() }

    private fun String.toPart2(): Long =
        this.split("""\s+""".toRegex()).filterIndexed { index, _ -> index != 0 }.joinToString("").toLong()


    private fun waysOfBeatingRecord(time: Long, distance: Long): Long {
        var wins: Long = 0

        for (t in 0..<time) {
            val remainingTime = time - t
            if (t * remainingTime > distance) {
                ++wins
            }
        }

        return wins
    }

    override fun part1() {
        val times = lines.first().toPart1()
        val distances = lines.last().toPart1()

        val result = times.zip(distances)
            .map { (time, distance) -> waysOfBeatingRecord(time, distance) }
            .reduce { acc, current -> acc * current }

        println(result)
    }

    override fun part2() {
        val time = lines.first().toPart2()
        val distance = lines.last().toPart2()

        val result = waysOfBeatingRecord(time, distance)

        println(result)
    }
}

fun main() {
    val s = Solution()
    s.part1()
    s.part2()
}

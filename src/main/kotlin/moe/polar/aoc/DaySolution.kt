package moe.polar.aoc

abstract class DaySolution {
    fun input(day: Int): String {
        return this::class.java.getResource("/day-$day.txt")?.readText()
            ?: error("Missing input file for day $day")
    }

    abstract fun part1()
    abstract fun part2()
}

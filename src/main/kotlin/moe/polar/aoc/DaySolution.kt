package moe.polar.aoc

import java.net.URL

abstract class DaySolution {
    fun input(day: Int): String {
        return this::class.java.getResource("/day-$day.txt")?.readText()
            ?: error("Missing input file for day $day")
    }

    fun resource(name: String): URL? {
        return this::class.java.getResource(name)
    }

    abstract fun part1()
    abstract fun part2()
}

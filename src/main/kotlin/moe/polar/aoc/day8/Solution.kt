package moe.polar.aoc.day8

import moe.polar.aoc.DaySolution

class Solution : DaySolution() {
    private val text = input(8)
    private val lines = text.lines()

    private val netEntryRegex = """(?<pos>\w{3})\s=\s\((?<first>\w{3}),\s(?<second>\w{3})\)""".toRegex()

    private val instructions = Array(lines.first().length) { i -> lines.first()[i].toInstruction() }
    private val network = HashMap<String, Pair<String, String>>().apply {
        for (i in 2..<lines.size) {
            val matches = netEntryRegex.matchEntire(lines[i])
            val pos = matches!!.groups["pos"]!!.value
            val first = matches.groups["first"]!!.value
            val second = matches.groups["second"]!!.value

            this[pos] = first to second
        }
    }

    override fun part1() {
        var currentPos = "AAA"

        var index = 0
        var steps = 0

        while (currentPos != "ZZZ") {
            val instruction = instructions[index]
            val entry = network[currentPos]!!

            currentPos = when (instruction) {
                Instruction.Left -> entry.first
                Instruction.Right -> entry.second
            }

            index = (index + 1) % instructions.size
            steps++
        }

        println(steps)
    }

    override fun part2() {
        TODO("Not yet implemented")
    }
}

fun main() {
    val s = Solution()
    s.part1()
}

package moe.polar.aoc.day4

import moe.polar.aoc.DaySolution

class Solution : DaySolution() {
    val lines = input(4).lines()
    val cards = lines.map(String::toCard)

    override fun part1() {
        println(cards.fold(0) { acc, card -> acc + card.points })
    }

    override fun part2() {
        TODO("Not yet implemented")
    }
}

fun main() {
    val s = Solution()
    s.part1()
}

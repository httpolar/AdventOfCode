package moe.polar.aoc.day7

import moe.polar.aoc.DaySolution

class Solution : DaySolution() {
    private val text = input(7)
    private val hands = text.lines().map(String::toHand).sorted()

    override fun part1() {
        var totalWinnings = 0

        // rank is index + 1 since hands sorted in ascending order
        hands.forEachIndexed { index: Int, hand: Hand ->
            totalWinnings += hand.bid * (index + 1)
        }

        println(totalWinnings)
    }

    override fun part2() {
        TODO("Not yet implemented")
    }
}

fun main() {
    val s = Solution()
    s.part1()
}

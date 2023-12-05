package moe.polar.aoc.day4

import moe.polar.aoc.DaySolution

class Solution : DaySolution() {
    private val lines = input(4).lines()
    private val cards = lines.map(String::toCard)

    override fun part1() {
        println(cards.fold(0) { acc, card -> acc + card.points })
    }

    override fun part2() {
        val cardCounter = IntArray(cards.size) { 1 }

        cards.indices.forEach { i: Int ->
            val card = cards[i]
            val copiesCount = card.matches

            if (copiesCount > 0) {
                repeat(cardCounter[i]) {
                    for (copy in i + 1..i + copiesCount) {
                        cardCounter[copy]++
                    }
                }
            }
        }

        println(cardCounter.sum())
    }
}

fun main() {
    val s = Solution()
    s.part1()
    s.part2()
}

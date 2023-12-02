package moe.polar.aoc.day2

import moe.polar.aoc.DaySolution

class Solution : DaySolution() {
    private val text = input(2)

    companion object {
        private const val MAX_RED: Int = 12
        private const val MAX_GREEN: Int = 13
        private const val MAX_BLUE: Int = 14
    }

    private data class GameSet(val red: Int, val green: Int, val blue: Int)
    private data class Game(val id: Int, val sets: List<GameSet>)

    private fun String.toGame(): Game {
        val (gameInfoRaw, gameSetsRaw) = this.split(": ")
        val id = gameInfoRaw.split(" ").last().toInt()

        val sets = gameSetsRaw.split("; ").map { gameSetRaw ->
            val counter = intArrayOf(0, 0, 0)

            gameSetRaw.split(", ").forEach { cubes ->
                val (count, cubeColour) = cubes.split(" ")
                when (cubeColour) {
                    "red" -> counter[0] = count.toInt()
                    "green" -> counter[1] = count.toInt()
                    "blue" -> counter[2] = count.toInt()
                }
            }

            GameSet(counter[0], counter[1], counter[2])
        }

        return Game(id, sets)
    }

    override fun part1() {
        var idsSum = 0

        text.lines().forEach { line ->
            val game = line.toGame()
            if (game.sets.all { it.red <= MAX_RED && it.green <= MAX_GREEN && it.blue <= MAX_BLUE }) {
                idsSum += game.id
            }
        }

        println(idsSum)
    }

    override fun part2() {
        var sumOfSetPowers = 0

        text.lines().forEach { line ->
            val neededCubesCount = intArrayOf(0, 0, 0)
            val game = line.toGame()

            game.sets.forEach { set ->
                if (set.red > neededCubesCount[0]) {
                    neededCubesCount[0] = set.red
                }

                if (set.green > neededCubesCount[1]) {
                    neededCubesCount[1] = set.green
                }

                if (set.blue > neededCubesCount[2]) {
                    neededCubesCount[2] = set.blue
                }
            }

            sumOfSetPowers += neededCubesCount[0] * neededCubesCount[1] * neededCubesCount[2]
        }

        println(sumOfSetPowers)
    }
}

fun main() {
    val s = Solution()
    s.part1()
    s.part2()
}

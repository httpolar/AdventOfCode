package moe.polar.aoc.day3

import moe.polar.aoc.DaySolution

class Solution : DaySolution() {
    private val text = input(3)
    private val lines = text.lines()
    private val arr = Array(lines.size) { row -> CharArray(lines.first().length) { col -> lines[row][col] } }

    private data class MatrixPos(var rowIdx: Int, var colIdx: Int)

    private fun MatrixPos.exists(): Boolean {
        return rowIdx >= 0 && rowIdx <= arr.lastIndex && colIdx >= 0 && colIdx <= arr.first().lastIndex
    }

    private data class DigitSequence(var rowIdx: Int, var startIdx: Int, var endIdx: Int)

    private fun DigitSequence.toInt(): Int {
        var num = arr[rowIdx][startIdx].digitToInt()
        if (startIdx == endIdx)
            return num

        for (colIdx in startIdx + 1..endIdx) {
            num *= 10
            num += arr[rowIdx][colIdx].digitToInt()
        }

        return num
    }

    private fun DigitSequence.hasNeighborSymbol(): Boolean {
        val neighbors = ArrayList<MatrixPos>()
        neighbors.add(MatrixPos(rowIdx, startIdx - 1))
        neighbors.add(MatrixPos(rowIdx, endIdx + 1))

        for (j in startIdx - 1..endIdx + 1) {
            neighbors.add(MatrixPos(rowIdx - 1, j))
            neighbors.add(MatrixPos(rowIdx + 1, j))
        }

        neighbors.forEach { pos ->
            if (pos.exists()) {
                val cell = arr[pos.rowIdx][pos.colIdx]

                if (!cell.isDigit() && cell != '.') {
                    return true
                }
            }
        }

        return false
    }

    override fun part1() {
        val rows = arr.size
        val cols = arr.first().size

        var rowIdx = 0
        var colIdx = 0

        var sum = 0

        while (rowIdx < rows) {
            var numStart = -1
            var numEnd = -1

            while (colIdx < cols) {
                val isCurrDigit = arr[rowIdx][colIdx].isDigit()
                val isNextDigit = colIdx + 1 < cols && arr[rowIdx][colIdx + 1].isDigit()

                val isStartFound = numStart != -1
                val isEndFound = numEnd != -1

                if (isCurrDigit && !isStartFound && !isEndFound) {
                    numStart = colIdx
                }

                if (isCurrDigit && !isNextDigit && !isEndFound) {
                    numEnd = colIdx
                }

                // "or" also checks if we found where number starts
                // but did not find its end index, however if the
                // current column is also the last column it means
                // that we actually found the whole number
                if (isStartFound && (isEndFound || colIdx == cols - 1)) {
                    val digits = DigitSequence(rowIdx, numStart, numEnd)
                    if (digits.hasNeighborSymbol()) {
                        sum += digits.toInt()
                    }

                    numStart = -1
                    numEnd = -1
                }

                colIdx++
            }

            colIdx = 0
            rowIdx++
        }

        println(sum)
    }

    override fun part2() {
        TODO("Not yet implemented")
    }
}

fun main() {
    val s = Solution()
    s.part1()
}

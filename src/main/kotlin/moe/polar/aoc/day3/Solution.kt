package moe.polar.aoc.day3

import moe.polar.aoc.DaySolution

class Solution : DaySolution() {
    private val text = input(3)
    private val lines = text.lines()
    private val arr = Array(lines.size) { row -> CharArray(lines.first().length) { col -> lines[row][col] } }


    private inner class MatrixPos(var rowIdx: Int, var colIdx: Int) {
        val value: Char? get() = arr.getOrNull(rowIdx)?.getOrNull(colIdx)

        fun getDigitSequenceOrNull(): DigitSequence? {
            var left = colIdx
            var foundLeft = false

            if (!arr[rowIdx][left].isDigit())
                return null

            var right = colIdx + 1
            var foundRight = false

            while (!foundLeft || !foundRight) {
                val chLeft = arr[rowIdx].getOrNull(left)
                val chRight = arr[rowIdx].getOrNull(right)

                val chNextLeft = arr[rowIdx].getOrNull(left - 1)
                val chNextRight = arr[rowIdx].getOrNull(right + 1)

                // make sure current characters are digits
                if (chLeft?.isDigit() != true) {
                    foundLeft = true
                    left++
                }

                if (chRight?.isDigit() != true) {
                    foundRight = true
                    right--
                }

                // check if next characters are digits
                if (!foundLeft && chNextLeft?.isDigit() != true) {
                    foundLeft = true
                }

                if (!foundRight && chNextRight?.isDigit() != true) {
                    foundRight = true
                }

                // move pointers
                if (!foundLeft) {
                    left--
                }

                if (!foundRight) {
                    right++
                }
            }

            return DigitSequence(rowIdx, left, right)
        }

        fun gearValue(): Int? {
            if (value == null)
                return null

            val ch = arr[rowIdx][colIdx]
            if (ch != '*')
                return null

            // TODO: could optimize this thing here so we avoid usage of Set later amd for that we need to find overlapping sequences
            val neighbors = ArrayList<MatrixPos>()

            for (i in rowIdx - 1..rowIdx + 1) {
                for (j in colIdx - 1..colIdx + 1) {
                    if (i == rowIdx && j == colIdx)
                        continue

                    val pos = MatrixPos(i, j)
                    if (pos.value != null) {
                        neighbors.add(pos)
                    }
                }
            }

            val sequences = mutableSetOf<DigitSequence>()

            neighbors.forEach { pos ->
                if (pos.value?.isDigit() == true) {
                    val digitSeq = pos.getDigitSequenceOrNull()
                    if (digitSeq != null) {
                        sequences.add(digitSeq)
                    }
                }
            }

            if (sequences.size != 2)
                return null

            return sequences.map { it.toInt() }.reduce { acc: Int, current: Int -> acc * current }
        }
    }

    private inner class DigitSequence(var rowIdx: Int, var startIdx: Int, var endIdx: Int) {
        fun toInt(): Int {
            var num = arr[rowIdx][startIdx].digitToInt()
            if (startIdx == endIdx)
                return num

            for (colIdx in startIdx + 1..endIdx) {
                num *= 10
                num += arr[rowIdx][colIdx].digitToInt()
            }

            return num
        }

        fun hasNeighborSymbol(): Boolean {
            val neighbors = ArrayList<MatrixPos>()
            neighbors.add(MatrixPos(rowIdx, startIdx - 1))
            neighbors.add(MatrixPos(rowIdx, endIdx + 1))

            for (j in startIdx - 1..endIdx + 1) {
                neighbors.add(MatrixPos(rowIdx - 1, j))
                neighbors.add(MatrixPos(rowIdx + 1, j))
            }

            neighbors.forEach { pos ->
                if (pos.value != null) {
                    val cell = arr[pos.rowIdx][pos.colIdx]

                    if (!cell.isDigit() && cell != '.') {
                        return true
                    }
                }
            }

            return false
        }
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
        val rows = arr.size
        val cols = arr.first().size

        var rowIdx = 0
        var colIdx = 0

        var sum = 0

        while (rowIdx < rows) {
            while (colIdx < cols) {
                val ch = arr[rowIdx][colIdx]
                if (ch == '*') {
                    MatrixPos(rowIdx, colIdx).gearValue()?.let { sum += it }
                }

                colIdx++
            }

            colIdx = 0
            rowIdx++
        }

        println(sum)
    }
}

fun main() {
    val s = Solution()
    s.part1()
    s.part2()
}

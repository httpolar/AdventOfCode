package moe.polar.aoc.day8

enum class Instruction(val value: Char) {
    Left('L'),
    Right('R');
}

fun Char.toInstruction() = when (this) {
    'L' -> Instruction.Left
    'R' -> Instruction.Right
    else -> error("Couldn't convert $this to an Instruction")
}

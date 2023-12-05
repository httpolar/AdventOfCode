package moe.polar.aoc.day4

data class Card(val id: Int, val winningNumbers: List<Int>, val currentNumbers: List<Int>) {
    companion object {
        val LINE_REGEX = Regex("""^Card\s+(?<id>\d+):\s+(?<win>[\d\s]+)\s\|\s+(?<current>[\d\s]+)$""")
    }

    val points: Int
        get() {
            var pointCounter = 0

            for (num in currentNumbers) {
                if (num !in winningNumbers)
                    continue

                if (pointCounter == 0) {
                    pointCounter = 1
                } else {
                    pointCounter += pointCounter
                }
            }

            return pointCounter
        }

    val matches: Int
        get() = currentNumbers.fold(0) { acc: Int, id: Int ->
            if (id in winningNumbers) {
                acc + 1
            } else {
                acc
            }
        }
}

fun String.toCard(): Card {
    val match = Card.LINE_REGEX.matchEntire(this)
        ?: error("Couldn't recognize card pattern in the string")

    val id = match.groups["id"]!!.value.toInt()
    val winningNumbers = match.groups["win"]!!.value.split("""\s+""".toRegex()).map(String::toInt)
    val currentNumbers = match.groups["current"]!!.value.split("""\s+""".toRegex()).map(String::toInt)

    return Card(id, winningNumbers, currentNumbers)
}

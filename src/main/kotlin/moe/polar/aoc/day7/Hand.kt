package moe.polar.aoc.day7

class Hand(val cards: List<Card>, val bid: Int) : Comparable<Hand> {
    init {
        if (cards.size > 5) {
            error("One hand can hold only 5 cards!")
        }
    }

    private val cardsCounter = cards.groupingBy { it.label }.eachCount()


    val type: HandType
        get() {
            if (cardsCounter.size == 1 && cardsCounter.containsValue(5)) {
                return HandType.FIVE_OF_A_KIND
            } else if (cardsCounter.size == 2 && cardsCounter.containsValue(4)) {
                return HandType.FOUR_OF_A_KIND
            } else if (cardsCounter.size == 2 && cardsCounter.containsValue(3) && cardsCounter.containsValue(2)) {
                return HandType.FULL_HOUSE
            } else if (cardsCounter.size == 3 && cardsCounter.containsValue(3) && cardsCounter.containsValue(1)) {
                return HandType.THREE_OF_A_KIND
            } else if (cardsCounter.size == 3 && cardsCounter.values.count { it == 2 } == 2) {
                return HandType.TWO_PAIR
            } else if (cardsCounter.size == 4 && cardsCounter.values.count { it == 2 } == 1) {
                return HandType.ONE_PAIR

            } else if (cardsCounter.size == 5) {
                return HandType.HIGH_CARD
            }


            error("Couldn't determine card type")
        }

    override fun compareTo(other: Hand): Int {
        val strengthComparison = this.type.strength.compareTo(other.type.strength)
        if (strengthComparison != 0) {
            return strengthComparison
        }

        for (i in cards.indices) {
            val cardsComparison = this.cards[i].compareTo(other.cards[i])
            if (cardsComparison != 0) {
                return cardsComparison
            }
        }

        error("Failed to compare hands")
    }
}

fun String.toHand(): Hand {
    val (cardsLabelsRaw, bidRaw) = split("""\s+""".toRegex())
    val bid = bidRaw.toInt()
    val cards = cardsLabelsRaw.toCharArray().map(::Card)

    return Hand(cards, bid)
}

package moe.polar.aoc.day7

class Card(val label: Char) : Comparable<Card> {
    private companion object {
        val order: CharArray = "23456789TJQKA".toCharArray()
    }

    override fun compareTo(other: Card): Int {
        val thisPower = order.indexOf(this.label)
        val otherPower = order.indexOf(other.label)

        return thisPower.compareTo(otherPower)
    }
}

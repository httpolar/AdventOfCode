package moe.polar.aoc.day7

/**
 * @see FIVE_OF_A_KIND
 * @see FOUR_OF_A_KIND
 * @see FULL_HOUSE
 * @see THREE_OF_A_KIND
 * @see TWO_PAIR
 * @see ONE_PAIR
 * @see HIGH_CARD
 */
enum class HandType(val strength: Int) {
    /** All five cards have the same label, such as `AAAAA` */
    FIVE_OF_A_KIND(6),
    /** Where four cards have the same label and one card has a different label: `AA8AA` */
    FOUR_OF_A_KIND(5),
    /** Where three cards have the same label, and the remaining two cards share a different label: `23332` */
    FULL_HOUSE(4),
    /** Where three cards have the same label, and the remaining two cards are each different from any other card in the hand: `TTT98` */
    THREE_OF_A_KIND(3),
    /** Where two cards share one label, two other cards share a second label, and the remaining card has a third label: `23432` */
    TWO_PAIR(2),
    /** Where two cards share one label, and the other three cards have a different label from the pair and each other: `A23A4` */
    ONE_PAIR(1),
    /** Where all cards' labels are distinct: `23456` */
    HIGH_CARD(0);
}

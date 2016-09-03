class GameModel {
    val deck = Deck()
    val wastePile: MutableList<Card> = mutableListOf();
    val foundationPiles = arrayOf(
            FoundationPile(clubs),
            FoundationPile(diamonds),
            FoundationPile(hearts),
            FoundationPile(spades)
    )
    val tableaufPiles = Array(7, { TableauPile() })

    fun resetGame() {
        wastePile.clear()
        foundationPiles.forEach {
            it.reset()
        }
        deck.reset()

        tableaufPiles.forEachIndexed { i, tableauPile ->
            val cards: MutableList<Card> = Array(i + 1, { deck.drawCard() }).toMutableList()
            tableaufPiles[i] = TableauPile(cards)
        }
    }
}


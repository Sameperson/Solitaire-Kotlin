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

    fun onDeckTap() {
        if (deck.cardsInDeck.size > 0) {
            val card = deck.drawCard()
            card.faceUp = true
            wastePile.add(card)
        }
        else {
            deck.cardsInDeck = wastePile.toMutableList()
            wastePile.clear()
        }
    }

    fun onWastePileTap() {
        if (wastePile.size > 0) {
            val card = wastePile.last()
            if (playCard(card)) {
                wastePile.remove(card)
            }
        }

    }

    private fun playCard(card: Card): Boolean {
        foundationPiles.forEach{
            if (it.addCard(card)) {
                return true
            }
        }
        tableaufPiles.forEach{
            if (it.addCards(mutableListOf(card))) {
                return true
            }
        }
        return false
    }
}


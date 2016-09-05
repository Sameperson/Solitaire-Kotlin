object GameModel {
    val deck = Deck()
    val foundationPile: MutableList<Card> = mutableListOf();
    val foundationPiles = arrayOf(
            FoundationPile(clubs),
            FoundationPile(diamonds),
            FoundationPile(hearts),
            FoundationPile(spades)
    )
    val tableauPiles = Array(7, { TableauPile() })

    fun resetGame() {
        foundationPile.clear()
        foundationPiles.forEach {
            it.reset()
        }
        deck.reset()

        tableauPiles.forEachIndexed { i, tableauPile ->
            val cards: MutableList<Card> = Array(i + 1, { deck.drawCard() }).toMutableList()
            tableauPiles[i] = TableauPile(cards)
        }
    }

    fun onDeckTap() {
        if (deck.cardsInDeck.size > 0) {
            val card = deck.drawCard()
            card.faceUp = true
            foundationPile.add(card)
        }
        else {
            deck.cardsInDeck = foundationPile.toMutableList()
            foundationPile.clear()
        }
    }

    fun onWastePileTap() {
        if (foundationPile.size > 0) {
            val card = foundationPile.last()
            if (playCard(card)) {
                foundationPile.remove(card)
            }
        }

    }

    fun onFoundationTap(index: Int) {
        val foundationPile = foundationPiles[index]
        if (foundationPile.cards.size > 0) {
            val card = foundationPile.cards.last()
            if (playCard(card)) {
                foundationPile.remove(card)
            }
        }

    }

    fun onTableauPileTap(tablIndex: Int, cardIndex: Int) {
        val pile = tableauPiles[tablIndex]
        if (pile.cards.size > 0) {
            val cards = pile.cards.subList(cardIndex, pile.cards.lastIndex + 1)
            if(playCards(cards)) {
                pile.removeCards(cardIndex)
            }

        }
        pile.cards[cardIndex]

    }

    private fun playCards(cards: MutableList<Card>): Boolean {
        if (cards.size == 1) {
            return playCard(cards.first())
        } else {
            tableauPiles.forEach{
                if (it.addCards(cards)) {
                    return true
                }
            }
        }
        return false
    }

    private fun playCard(card: Card): Boolean {
        foundationPiles.forEach{
            if (it.addCard(card)) {
                return true
            }
        }
        tableauPiles.forEach{
            if (it.addCards(mutableListOf(card))) {
                return true
            }
        }
        return false
    }
}


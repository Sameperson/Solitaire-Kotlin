import org.junit.Test

import org.junit.Assert.*

class TableauPileTest {
    @Test
    fun addCards() {
        val tableauPile = TableauPile(mutableListOf(Card(12, spades)))
        val cards = mutableListOf(Card(11, hearts))
        tableauPile.addCards(cards)
        assertEquals(2, tableauPile.cards.size)
    }

    @Test
    fun removeCards() {

    }

}
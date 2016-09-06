import org.junit.Test;

class GameTest {

    @Test
    fun kingInFirstFoundationPile() {
        var numGames = 0
        val maxGames = 10000
        for (i in 1..maxGames) {
            numGames++
            GameModel.resetGame()
            for (j in 1..100) {
                GamePresenter.onDeckTap()
                GamePresenter.onWastePileTap()
                GameModel.tableauPiles.forEachIndexed { index, tableauPile ->
                    GamePresenter.onTableauPileTap(index, tableauPile.cards.lastIndex)
                }
            }
            if (GameModel.foundationPiles[0].cards.size == 13) {
                break
            }
        }
        GameModel.debugPrint()
        println("# Games: $numGames")
        assert(numGames < maxGames)
    }
}

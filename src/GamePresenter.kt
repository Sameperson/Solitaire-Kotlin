object GamePresenter {
    var view: GameView? = null

    fun setGameView(gameView: GameView) {
        view = gameView

    }

    fun onDeckTap() {
        GameModel.onDeckTap()
        view?.update()
    }

    fun onWastePileTap() {
        GameModel.onWastePileTap()
        view?.update()
    }

    fun onFoundationTap(index: Int) {
        GameModel.onFoundationTap(index)
        view?.update()
    }

    fun onTableauPileTap(tablIndex: Int, cardIndex: Int) {
        GameModel.onTableauPileTap(tablIndex, cardIndex)
        view?.update()

    }

}
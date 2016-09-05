class GamePresenter {
    var view: GameView? = null

    fun setGameView(gameView: GameView) {
        view = gameView

    }

    fun onDeckTap() {
        GameModel.onDeckTap()
        view?.update(GameModel)
    }

}
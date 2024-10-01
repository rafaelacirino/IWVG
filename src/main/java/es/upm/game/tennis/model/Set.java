package es.upm.game.tennis.model;

import es.upm.game.tennis.controller.GameController;

import java.util.ArrayList;
import java.util.List;

public class Set {

    private AbstractGame currentGame;
    private final List<AbstractGame> games;
    private final GameController gameController;

    public Set(Player playerService, Player playerRest) {
        this.games = new ArrayList<>();
        this.gameController = new GameController();
        this.currentGame = gameController.createGame(false, playerService, playerRest);
        this.games.add(currentGame);
    }

    public AbstractGame getCurrentGame() {
        return currentGame;
    }

    public void addGame(AbstractGame game) {
        games.add(game);
    }

    public void updateGame(Player playerService, Player playerRest) {
        this.currentGame = gameController.createGame(isTieBreak(), playerService, playerRest);
        addGame(currentGame);
    }

    private boolean isTieBreak() {
        int player1Games = (int) games.stream().filter(AbstractGame::isPlayer0Service).count();
        int player2Games = games.size() - player1Games;

        return player1Games == 6 && player2Games == 6;
    }

}
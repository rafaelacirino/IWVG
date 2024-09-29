package es.upm.game.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class Set {

    private IGame currentGame;
    private List<IGame> games;
    private GameFactory gameFactory;

    public Set(Player playerService, Player playerRest) {
        this.games = new ArrayList<>();
        this.gameFactory = new GameFactory();
        this.currentGame = gameFactory.createGame(false, playerService, playerRest);
        this.games.add(currentGame);
    }

    public IGame getCurrentGame() {
        return currentGame;
    }

    public void addGame(IGame game) {
        games.add(game);
    }

    public void updateGame(Player playerService, Player playerRest) {
        this.currentGame = gameFactory.createGame(isTieBreak(), playerService, playerRest);
        addGame(currentGame);
    }

    private boolean isTieBreak() {
        int player1Games = (int) games.stream().filter(g -> g.isPlayer0Service()).count();
        int player2Games = games.size() - player1Games;

        return player1Games == 6 && player2Games == 6;
    }

}
package es.upm.game.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class Set {

    private final int playerServiceGamesWon;
    private final int playerRestGamesWon;
    private Game currentGame;
    private List<Game> games;

    public Set(Player playerService, Player playerRest) {
        this.games = new ArrayList<>();
        this.playerServiceGamesWon = 0;
        this.playerRestGamesWon = 0;
        this.currentGame = new Game(playerService, playerRest);
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void addGame(Game game) {
        games.add(game);
    }

}
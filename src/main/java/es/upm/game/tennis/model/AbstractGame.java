package es.upm.game.tennis.model;

import es.upm.game.tennis.utils.ConstantsUtil;

import java.util.List;

public abstract class AbstractGame {
    protected final List<Player> players;
    protected boolean isPlayer0Service;
    protected final GameScore gameScore;

    protected AbstractGame(Player playerService, Player playerRest) {
        this.players = List.of(playerService, playerRest);
        this.isPlayer0Service = true;
        this.gameScore = new GameScore();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isPlayer0Service() {
        return isPlayer0Service;
    }

    public void resetPoints() {
        gameScore.resetPoints();
    }

    public abstract boolean isGameOver();

    public void onGameOver() {
        System.out.println(ConstantsUtil.GAME_BALL);
    }
}
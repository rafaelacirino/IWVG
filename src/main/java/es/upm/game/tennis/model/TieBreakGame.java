package es.upm.game.tennis.model;

public class TieBreakGame extends AbstractGame {

    public TieBreakGame(Player playerService, Player playerRest) {
        super(playerService, playerRest);
    }

    @Override
    public boolean isGameOver() {
        return (Math.max(gameScore.getPoints()[0], gameScore.getPoints()[1]) >= 6 &&
                Math.abs(gameScore.getPoints()[0] - gameScore.getPoints()[1]) >= 2);
    }
}
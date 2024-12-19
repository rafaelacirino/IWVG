package es.upm.game.tennis.model;

public class StandardGame extends AbstractGame {

    public StandardGame(Player playerService, Player playerRest) {
        super(playerService, playerRest);
    }

    @Override
    public boolean isGameOver() {
        return (Math.max(gameScore.getPoints()[0], gameScore.getPoints()[1]) >= 4 &&
                Math.abs(gameScore.getPoints()[0] - gameScore.getPoints()[1]) >= 2);
    }
}
package es.upm.game.tennis.model;

public class GameFactory {
    public IGame createGame(boolean isTieBreak, Player playerService, Player playerRest) {
        if (isTieBreak) {
            return new TieBreakGame(playerService, playerRest);
        } else {
            return new StandardGame(playerService, playerRest);
        }
    }
}

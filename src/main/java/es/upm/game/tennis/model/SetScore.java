package es.upm.game.tennis.model;

public class SetScore {

    private final int[] gamesWon = {0, 0};

    public void addGame(int playerIndex) {
        gamesWon[playerIndex]++;
    }

    public boolean isSetOver() {
        return Math.max(gamesWon[0], gamesWon[1]) >= 6 && Math.abs(gamesWon[0] - gamesWon[1]) >= 2;
    }

    public void resetGames() {
        gamesWon[0] = 0;
        gamesWon[1] = 0;
    }

    public int[] getGamesWon() {
        return gamesWon;
    }
}
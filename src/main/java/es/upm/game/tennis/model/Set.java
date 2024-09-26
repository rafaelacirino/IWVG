package es.upm.game.tennis.model;

public class Set {

    private int playerServiceGamesWon;
    private int playerRestGamesWon;

    public Set() {
        this.playerServiceGamesWon = 0;
        this.playerRestGamesWon = 0;
    }

    public boolean isSetOver() {
        return (playerServiceGamesWon >= 6 || playerRestGamesWon >= 6) && Math.abs(playerServiceGamesWon - playerRestGamesWon) >= 2;
    }
}
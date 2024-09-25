package es.upm.game.tennis.model;

public class Set {

    private int playerServiceGamesWon;
    private int playerRestGamesWon;
    private boolean tieBreakActive;

    public Set() {
        this.playerServiceGamesWon = 0;
        this.playerRestGamesWon = 0;
        this.tieBreakActive = false;
    }

    public int getPlayerServiceGamesWon() {
        return playerServiceGamesWon;
    }

    public int getPlayerRestGamesWon() {
        return playerRestGamesWon;
    }

    public void playerServiceWinGame() {
        playerServiceGamesWon++;
    }

    public void playerRestWinGame() {
        playerRestGamesWon++;
    }

    public boolean isSetOver() {
        return (playerServiceGamesWon >= 6 || playerRestGamesWon >= 6) && Math.abs(playerServiceGamesWon - playerRestGamesWon) >= 2;
    }

    public boolean isTieBreakRequired() {
        return playerServiceGamesWon == 6 && playerRestGamesWon == 6;
    }

//    public String getSetScore() {
//        return playerServiceGamesWon + " - " + playerRestGamesWon;
//    }
}
package es.upm.game.tennis.model;

public class TieBreak extends Game {
    private int playerServicePoints;
    private int playerRestPoints;

    public TieBreak(Player playerService, Player playerRest) {
        super(playerService, playerRest);
        this.playerServicePoints = 0;
        this.playerRestPoints = 0;
    }

    @Override
    public void addPoint(Player player) {
        if (player.equals(this.getPlayerService())) {
            playerServicePoints++;
        } else {
            playerRestPoints++;
        }
    }

    public boolean checkTieBreakWinner() {
        if (playerServicePoints >= 6 && playerServicePoints - playerRestPoints >= 2) {
            return true;
        }
        if (playerRestPoints >= 6 && playerRestPoints - playerServicePoints >= 2) {
            return true;
        }
        return false;
    }

    public String getScore() {
        return playerServicePoints + " - " + playerRestPoints;
    }
}
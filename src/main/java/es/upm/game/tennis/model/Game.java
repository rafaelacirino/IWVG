package es.upm.game.tennis.model;

public class Game {

    private Player playerService;
    private Player playerRest;


    public Game(Player playerService, Player playerRest) {
        this.playerService = playerService;
        this.playerRest = playerRest;
    }

    public Game() {}

    public Player getPlayerService() {
        return playerService;
    }

    public void addPoint(Player player) {
        player.scorePoint();
    }

    public boolean isGameOver() {
        return checkGameWinner(playerService) || checkGameWinner(playerRest);
    }

    private boolean checkGameWinner(Player player) {
        return player.getCurrentPoints() >= 4 &&
                (player.getCurrentPoints() - getOpponent(player).getCurrentPoints() >= 2);
    }

    private Player getOpponent(Player player) {
        return (player == playerService) ? playerRest : playerService;
    }
}
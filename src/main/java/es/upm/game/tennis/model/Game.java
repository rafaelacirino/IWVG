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

    public void setPlayerService(Player playerService) {
        this.playerService = playerService;
    }

    public Player getPlayerRest() {
        return playerRest;
    }

    public void setPlayerRest(Player playerRest) {
        this.playerRest = playerRest;
    }

    public void addPoint(Player player) {
        player.scorePoint();
        checkGameWinner(player);
    }

    public void checkGameWinner(Player player) {
        if (player.getCurrentPoints() >= 4) {
            player.winGamePerSet(0); // Atualiza o set atual, exemplo.
            resetPoints();
        }
    }

    public void resetPoints() {
        playerService.resetPoints();
        playerRest.resetPoints();
    }
}
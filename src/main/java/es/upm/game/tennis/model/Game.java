package es.upm.game.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> players;
    private boolean isPlayer0Service;

    public Game(Player playerService, Player playerRest) {
        this.players = new ArrayList<>();
        this.players.add(playerService);
        this.players.add(playerRest);
        this.isPlayer0Service = true;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isPlayer0Service() {
        return isPlayer0Service;
    }

    public void switchRoles() {
        isPlayer0Service = !isPlayer0Service;
    }

//    private boolean checkGameWinner(Player player) {
//        return player.getCurrentPoints() >= 4 &&
//                (player.getCurrentPoints() - getOpponent(player).getCurrentPoints() >= 2);
//    }

//    private Player getOpponent(Player player) {
//        return (player == playerService) ? playerRest : playerService;
//    }


}
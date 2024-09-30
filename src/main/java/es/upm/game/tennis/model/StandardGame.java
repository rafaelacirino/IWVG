package es.upm.game.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class StandardGame implements IGame {
    private final List<Player> players;
    private boolean isPlayer0Service;

    public StandardGame(Player playerService, Player playerRest) {
        this.players = new ArrayList<>();
        this.players.add(playerService);
        this.players.add(playerRest);
        this.isPlayer0Service = true;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean isPlayer0Service() {
        return isPlayer0Service;
    }

    @Override
    public void switchRoles() {
        isPlayer0Service = !isPlayer0Service;
    }
}
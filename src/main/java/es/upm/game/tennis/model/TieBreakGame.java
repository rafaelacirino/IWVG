package es.upm.game.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class TieBreakGame implements IGame {
    private List<Player> players;
    private boolean isPlayer0Service;

    public TieBreakGame(Player playerService, Player playerRest) {
        this.players = new ArrayList<>();
        this.players.add(playerService);
        this.players.add(playerRest);
        this.isPlayer0Service = true;
    }

    @Override
    public List<Player> getPlayers() {
        return List.of();
    }

    @Override
    public boolean isPlayer0Service() {
        return isPlayer0Service;
    }

    @Override
    public void switchRoles() {
        isPlayer0Service = !isPlayer0Service;
    }

    @Override
    public void updatePoints(Player player) {
        // Update logic for TieBreak game points
    }

    @Override
    public boolean isGameOver() {
        // Game-over logic for TieBreak games
        return false;
    }
}
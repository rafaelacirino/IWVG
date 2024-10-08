package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerController {

    private final List<Player> players;

    public PlayerController() {
        players = new ArrayList<>();
    }

    public Player createPlayer(String name) {
        Player player = new Player(players.size() + 1, name);
        players.add(player);
        return player;
    }

    public Optional<Player> getPlayerById(int id) {
        return players.stream()
                .filter(player -> player.getId() == id)
                .findFirst();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
package es.upm.game.tennis.model;

import java.util.List;

public interface IGame {
    List<Player> getPlayers();
    boolean isPlayer0Service();
    void switchRoles();
}

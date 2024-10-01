package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Player;

import java.util.logging.Logger;

public class PlayerView {

    public void displayPlayerCreated(Player player) {
        Logger.getLogger(PlayerView.class.getName()).info("Player created: " + player.getName());
    }
}
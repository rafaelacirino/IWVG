package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Player;

import java.util.logging.Logger;

public class PlayerView {

    private static final Logger logger = Logger.getLogger(PlayerView.class.getName());

    public void displayPlayerCreated(Player player) {
        logger.info("Player created: " + player.getName());
    }
}
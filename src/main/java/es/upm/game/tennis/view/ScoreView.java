package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Player;

import java.util.logging.Logger;

public class ScoreView {

    private static final Logger logger = Logger.getLogger(RefereeView.class.getName());

    public void displayPointToServer(Player playerService) {
        logger.info("Point to Server: " + playerService.getName());
    }

    public void displayPointToReceiver(Player playerRest) {
        logger.info("Point to Receiver: " + playerRest.getName());
    }

    public void displayGameOver() {
        logger.info("Game Over. Switching roles...");
    }

    public void displayLackService(Player playerService) {
        logger.info("Lack of service by: " + playerService.getName());
    }
}
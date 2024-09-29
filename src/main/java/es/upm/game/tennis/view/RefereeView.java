package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Referee;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RefereeView {

    private static final Logger logger = Logger.getLogger(RefereeView.class.getName());


    public void displayRefereeCreated(Referee referee) {
        logger.info("Referee created: " + referee.getName());
    }

    public void displayLoginStatus(boolean success) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Login %s", success ? "successful" : "failed"));
        }
    }
}
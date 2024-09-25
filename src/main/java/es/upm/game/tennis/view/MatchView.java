package es.upm.game.tennis.view;

import es.upm.game.tennis.controller.MatchController;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchView {

    private static final Logger logger = Logger.getLogger(MatchView.class.getName());

    public void displayInitialMatch(MatchController matchController) {
        String matchScore = matchController.getMatchScore();
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Current Match Score:%n%s", matchScore));
        }
    }

    public void displayMatchScore(String matchScore) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Current Match Score: %s", matchScore));
        }
    }

    public void promptCommand() {
        System.out.print("> ");
    }

    public void displayPlayerCreated(Player player) {
        logger.info("Player created: " + player.getName());
    }

    public void displayRefereeCreated(Referee referee) {
        logger.info("Referee created: " + referee.getName());
    }

    public void displayLoginStatus(boolean success) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Login %s", success ? "successful" : "failed"));
        }
    }

    public void displayGameBallMessage() {
        logger.info("Game ball!!!");
    }

    public void displaySetBallMessage() {
        logger.info("Set ball!!!");
    }

    public void displayMatchBallMessage() {
        logger.info("Match ball!!!");
    }
}
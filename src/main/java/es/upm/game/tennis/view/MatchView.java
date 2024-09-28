package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.controller.ScoreController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchView {

    private static final Logger logger = Logger.getLogger(MatchView.class.getName());

    public void displayInitialMatch(ScoreController scoreController) {
        StringBuilder matchScore = new StringBuilder();
        matchScore.append(String.format("date: %s%n", scoreController.getMatch().getDate()))
                  .append(scoreController.getMatch().getMatchScore());
        logger.info(String.format("Init Match:%n%s", matchScore));
    }

    public void displayMatchScore(ScoreController scoreController) {
        if (scoreController.getMatch() != null) {
            String matchScore = scoreController.getMatch().getMatchScore();
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Current Match Score: %s", matchScore));
            }
        } else {
            logger.warning("The match has not started yet");
        }
    }

    public void displayMatchResult(ScoreController scoreController) {
        if (scoreController.getMatch() != null) {
            String matchScore = scoreController.getMatch().getMatchScore();
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Results Match:%n%s", matchScore));
            }
        } else {
            logger.warning("The match has not started yet");
        }
    }

    public void displayPlayerCreated(Player player) {
        logger.info("Player created: " + player.getName());
    }
}
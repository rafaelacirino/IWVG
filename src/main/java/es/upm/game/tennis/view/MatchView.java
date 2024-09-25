package es.upm.game.tennis.view;

import es.upm.game.tennis.controller.MatchController;
import es.upm.game.tennis.model.Match;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchView {

    private static final Logger logger = Logger.getLogger(MatchView.class.getName());
    private Match match;
    private static MatchView instance;

    private MatchView() {
    }

    public static MatchView getInstance() {
        if (instance == null) {
            instance = new MatchView();
        }
        return instance;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void displayMatchInitial() {
        StringBuilder matchScore = new StringBuilder();
        matchScore.append(String.format("id: %d%n", match.getId()))
                .append(String.format("date: %s%n", match.getDate()))
                .append(match.getMatchScore());
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Init Match:%n%s", matchScore));
        }
    }

    public void displayMatchScore() {
        if (match != null) {
            String matchScore = match.getMatchScore();
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Current Match Score: %s", matchScore));
            }
        } else {
            logger.warning("The match has not started yet");
        }
    }

    public void displayMatchResult() {
        if (match != null) {
            String matchScore = match.getMatchScore();
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
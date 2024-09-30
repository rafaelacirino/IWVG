package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.controller.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchView {

    private static final Logger logger = Logger.getLogger(MatchView.class.getName());

    public String getMatchScore(MatchController matchController) {
        String currentServer = matchController.getMatch().getCurrentSet()
                .getCurrentGame().isPlayer0Service() ? "*" : " ";
        String currentReceiver = !matchController.getMatch().getCurrentSet()
                .getCurrentGame().isPlayer0Service() ? "*" : " ";

        Player player1 = matchController.getMatch().getCurrentSet().getCurrentGame().getPlayers().get(0);
        Player player2 = matchController.getMatch().getCurrentSet().getCurrentGame().getPlayers().get(1);

        return String.format("%n%s %s: %s%n", currentServer, player1.getName(), matchController.getMatch()
                        .getScoreBoard().getCurrentPoints()[0]) +
               String.format("%s %s: %s%n", currentReceiver, player2.getName(), matchController.getMatch()
                        .getScoreBoard().getCurrentPoints()[1]);
    }

    public void displayInitialMatch(MatchController matchController) {
        StringBuilder matchScore = new StringBuilder();
        matchScore.append(String.format("date: %s%n", matchController.getMatch().getDate()))
                  .append(getMatchScore(matchController));
        if (logger.isLoggable(Level.INFO)) {
        logger.info(String.format("Init Match:%n%s", matchScore));
        }
    }

    public void displayMatchScore(MatchController matchController) {
        String matchScore = getMatchScore(matchController);
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Current Match Score: %s", matchScore));
        }
    }

    public void displayMatchResult(MatchController matchController) {
        String matchScore = getMatchScore(matchController);
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Results Match:%n%s", matchScore));
        }
    }
}
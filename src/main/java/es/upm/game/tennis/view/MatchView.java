package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Match;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;
import es.upm.game.tennis.controller.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchView {

    private static final Logger logger = Logger.getLogger(MatchView.class.getName());

    public String getMatchScore(Match match) {
        String currentServer = match.getCurrentSet().getCurrentGame().isPlayer0Service() ? "*" : " ";
        String currentReceiver = !match.getCurrentSet().getCurrentGame().isPlayer0Service() ? "*" : " ";

        Player player1 = match.getCurrentSet().getCurrentGame().getPlayers().get(0);
        Player player2 = match.getCurrentSet().getCurrentGame().getPlayers().get(1);

        StringBuilder scoreBuilder = new StringBuilder();
        scoreBuilder.append(String.format("%n%s %s: %s%n", currentServer, player1.getName(), match.getScoreBoard().getCurrentPoints()[0]))
                .append(String.format("%s %s: %s%n", currentReceiver, player2.getName(), match.getScoreBoard().getCurrentPoints()[1]));

        return scoreBuilder.toString();
    }

    public void displayInitialMatch(Match match) {
        StringBuilder matchScore = new StringBuilder();
        matchScore.append(String.format("id: %s%n", match.getId()))
                  .append(String.format("date: %s%n", match.getDate()))
                  .append(getMatchScore(match));
        logger.info(String.format("Init Match:%n%s", matchScore));
    }

    public void displayMatchScore(Match match) {
        String matchScore = getMatchScore(match);
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Current Match Score: %s", matchScore));
        }
    }

    // cambiar formato de resultado final del partido. Quitar getMatchScore();
    public void displayMatchResult() {
//        String matchScore = getMatchScore();
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Results Match:%n%s", "matchScore"));
        }
    }

    public void displayRefereeCreated(Referee referee) {
        logger.info("Referee created: " + referee.getName());
    }

    public void displayLoginStatus(boolean success) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Login %s", success ? "successful" : "failed"));
        }
    }

    public void displayPlayerCreated(Player player) {
        logger.info("Player created: " + player.getName());
    }

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
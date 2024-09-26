package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchView {

    private static final Logger logger = Logger.getLogger(MatchView.class.getName());
    private Scanner scanner;

    public void displayInitialMatch(String matchScore) {
        logger.info("Initial Match Score:");
        logger.info(matchScore);
    }

    public void displayMatchScore(String matchScore) {
        logger.info("Current Match Score:");
        logger.info(matchScore);
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

    public void displayServer(Player playerService){
        logger.info("Current server: " + playerService.getName());
    }

    public void displayMatchStarted(Player player1, Player player2) {
        logger.info("Match started between " + player1.getName() + " and " + player2.getName());
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

    public String readPlayerName(int playerNumber) {
        logger.info("Enter name of Player " + playerNumber + ":");
        return scanner.nextLine();
    }

    public int readTotalSets() {
        logger.info("Enter total number of sets:");
        return scanner.nextInt();
    }

    public int readPlayerId(int i) {
        logger.info("Enter id Player" + i + ":");
        return scanner.nextInt();
    }

    public void displayPlayers(Player playerService, Player playerRest) {
        logger.info("Current Players in the Match:");
        logger.info("Server: " + playerService.getName());
        logger.info("Receiver: " + playerRest.getName());
    }

    public void displayLackService(Player playerService) {
        logger.info("Lack of service by: " + playerService.getName());
        logger.info("Lack of service by: " + playerService.getName());
    }
}
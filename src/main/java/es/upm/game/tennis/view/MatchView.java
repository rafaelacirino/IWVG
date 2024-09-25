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
        System.out.println("Initial Match Score:");
        System.out.println(matchScore);
    }

    public void displayMatchScore(String matchScore) {
        System.out.println("Current Match Score:");
        System.out.println(matchScore);
    }

    public void promptCommand() {
        System.out.print("> ");
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
        System.out.println("Enter name of Player " + playerNumber + ":");
        return scanner.nextLine();
    }

    public int readTotalSets() {
        System.out.println("Enter total number of sets:");
        return scanner.nextInt();
    }

    public int readPlayerId(int i) {
        logger.info("Enter id Player" + i + ":");
        return scanner.nextInt();
    }

    public void displayPlayers(Player playerService, Player playerRest) {
        System.out.println("Current Players in the Match:");
        System.out.println("Server: " + playerService.getName());
        System.out.println("Receiver: " + playerRest.getName());
    }

    public void displayLackService(Player playerService) {
        logger.info("Lack of service by: " + playerService.getName());
        System.out.println("Lack of service by: " + playerService.getName());
    }
}
package es.upm.game.tennis;

import es.upm.game.tennis.controller.*;
import es.upm.game.tennis.model.Match;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;
import es.upm.game.tennis.service.MatchService;
import es.upm.game.tennis.view.MatchView;

import java.util.Scanner;
import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final String ENTER_REFEREE_NAME = "Enter referee name: ";
    private static final String ENTER_REFEREE_PASSWORD = "Enter referee password: ";
    private static final String ENTER_PLAYER_NAME = "Enter player name: ";
    private static final String ENTER_PLAYER_ID = "Enter player id: ";
    private static final String ENTER_TOTAL_SETS = "Enter number of sets: ";

    private static final Scanner scanner = new Scanner(System.in);

    private static boolean isLoggedIn = false;
    private static boolean isMatchCreated = false;

    private static String getInput(String promptMessage) {
        logger.info(promptMessage);
        return scanner.nextLine();
    }

    private static int getInputNumber(String promptMessage) {
        logger.info(promptMessage);
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public static void main(String[] args) {

        MatchView matchView = new MatchView();
        PlayerController playerController = new PlayerController();
        RefereeController refereeController = new RefereeController();
        Match match = new Match();
        MatchService matchService = new MatchService(match);
        MatchController matchController = null;

        boolean running = true;

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine();

            switch (command) {
                case "createReferee":
                    String refName = getInput(ENTER_REFEREE_NAME);
                    String refPassword = getInput(ENTER_REFEREE_PASSWORD);
                    Referee referee = refereeController.createReferee(refName, refPassword);
                    matchView.displayRefereeCreated(referee);
                    break;

                case "login":
                    String loginName = getInput(ENTER_REFEREE_NAME);
                    String loginPassword = getInput(ENTER_REFEREE_PASSWORD);
                    isLoggedIn = refereeController.login(loginName, loginPassword);
                    matchView.displayLoginStatus(isLoggedIn);
                    break;

                case "createPlayer":
                    String playerName = getInput(ENTER_PLAYER_NAME);
                    Player player = playerController.createPlayer(playerName);
                    matchView.displayPlayerCreated(player);
                    break;

                case "readPlayer":
                    int playerId = getInputNumber(ENTER_PLAYER_ID);
                    Optional<Player> foundPlayer = playerController.getPlayerById(playerId);

                    foundPlayer.ifPresentOrElse(
                            p -> logger.info("Player ID: " + p.getId() + ", Name: " + p.getName()),
                            () -> logger.warning("Player not found.")
                    );
                    break;

                case "createMatch":
                    if (!isLoggedIn) {
                        logger.warning("No referee logged in");
                        break;
                    }

                    int totalSets = getInputNumber(ENTER_TOTAL_SETS);
                    int id1 = getInputNumber(ENTER_PLAYER_ID);
                    int id2 = getInputNumber(ENTER_PLAYER_ID);

                    Player player1 = playerController.getPlayerById(id1).orElse(null);
                    Player player2 = playerController.getPlayerById(id2).orElse(null);

                    if (player1 != null && player2 != null) {
                        Match newMatch = new Match(totalSets, player1, player2);
                        matchController = new MatchController(newMatch, matchView, matchService);
                        matchController.createMatch(totalSets, player1, player2);
                    } else {
                        logger.warning("One or both players not found.");
                    }
                    break;

                case "readPlayers":
                    matchController.readPlayers();
                    break;

                case "lackService":
                    if (matchController != null) {
                        matchController.lackService();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "pointService":
                    if (matchController != null) {
                        matchController.pointService();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "pointRest":
                    if (matchController != null) {
                        matchController.pointRest();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "addPointToServer":
                    if (matchController != null) {
                        matchController.pointToServer();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "addPointToReceiver":
                    if (matchController != null) {
                        matchController.pointToReceiver();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "displayScore":
                    if (matchController != null) {
                        matchController.displayScore();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "endMatch":
                    if (matchController != null && matchService.isGameOver()) {
                        System.out.println("The match has ended.");
                    } else {
                        System.out.println("Match is still ongoing or no match has been started.");
                    }
                    break;

                case "logout":
                    running = false;
                    logger.info("Exiting...");
                    break;

                default:
                    logger.warning("Unknown command.");
                    break;
            }
        }
    }
}
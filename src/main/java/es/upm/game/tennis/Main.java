package es.upm.game.tennis;

import es.upm.game.tennis.controller.*;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;
import es.upm.game.tennis.view.MatchView;
import es.upm.game.tennis.view.PlayerView;
import es.upm.game.tennis.view.RefereeView;

import java.util.Scanner;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final String ENTER_REFEREE_NAME = "Enter referee name: ";
    private static final String ENTER_REFEREE_PASSWORD = "Enter referee password: ";
    private static final String ENTER_PLAYER_NAME = "Enter player name: ";
    private static final String ENTER_PLAYER_ID = "Enter player id: ";
    private static final String ENTER_TOTAL_SETS = "Enter number of sets: ";

    private static final Scanner scanner = new Scanner(System.in);

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

    private static final Map<String, Consumer<MatchController>> pointActions = new HashMap<>();

    static {
        pointActions.put("lackService", MatchController::lackService);
        pointActions.put("pointService", MatchController::pointService);
        pointActions.put("pointRest", MatchController::pointRest);
    }

    public static void main(String[] args) {

        MatchView matchView = new MatchView();
        RefereeView refereeView = new RefereeView();
        PlayerView playerView = new PlayerView();
        PlayerController playerController = new PlayerController();
        RefereeController refereeController = new RefereeController();
        MatchController matchController = null;
        boolean isLoggedIn = false;
        boolean isMatchCreated = false;

        boolean running = true;

        while (running) {
            logger.info("> ");
            String command = scanner.nextLine();

            switch (command) {
                case "createReferee":
                    String refName = getInput(ENTER_REFEREE_NAME);
                    String refPassword = getInput(ENTER_REFEREE_PASSWORD);
                    Referee referee = refereeController.createReferee(refName, refPassword);
                    refereeView.displayRefereeCreated(referee);
                    break;

                case "login":
                    String loginName = getInput(ENTER_REFEREE_NAME);
                    String loginPassword = getInput(ENTER_REFEREE_PASSWORD);
                    isLoggedIn = refereeController.login(loginName, loginPassword);
                    refereeView.displayLoginStatus(isLoggedIn);
                    break;

                case "createPlayer":
                    String playerName = getInput(ENTER_PLAYER_NAME);
                    Player player = playerController.createPlayer(playerName);
                    playerView.displayPlayerCreated(player);
                    break;

                case "readPlayer":
                    int playerId = getInputNumber(ENTER_PLAYER_ID);
                    Optional<Player> foundPlayer = playerController.getPlayerById(playerId);

                    foundPlayer.ifPresentOrElse(
                            p -> logger.info("Player ID: " + p.getId() + ", Name: " + p.getName()),
                            () -> logger.warning("Player not found.")
                    );
                    break;

                case "readPlayers":
                    List<Player> players = playerController.getPlayers();
                    for (Player p : players) {
                        logger.info("Player ID: " + p.getId() + ", Name: " + p.getName());
                    }
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
                        matchController = new MatchController(matchView);

                        matchController.createMatch(totalSets, player1, player2);
                        matchView.displayInitialMatch(matchController);
                        isMatchCreated = true;
                    } else {
                        logger.warning("One or both players not found.");
                    }
                    break;

                case "lackService", "pointService", "pointRest":
                    if (!isMatchCreated) {
                        logger.warning("Must create a match before adding points");
                        break;
                    }
                    pointActions.get(command).accept(matchController);
                    matchView.displayMatchScore(matchController);
                    break;

                case "readMatch":
                    matchView.displayMatchResult(matchController);
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
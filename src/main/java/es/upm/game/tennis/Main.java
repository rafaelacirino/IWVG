package es.upm.game.tennis;

import es.upm.game.tennis.controller.*;
import es.upm.game.tennis.model.Match;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;
import es.upm.game.tennis.view.MatchView;

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

//    private static ScoreController scoreController = null;

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

    private static final Map<String, Consumer<ScoreController>> pointActions = new HashMap<>();

    static {
        pointActions.put("lackService", scoreController -> scoreController.lackService());
        pointActions.put("pointService", scoreController -> scoreController.pointService());
        pointActions.put("pointRest", scoreController -> scoreController.pointRest());
    }

    public static void main(String[] args) {

        MatchView matchView = new MatchView();
        PlayerController playerController = new PlayerController();
        RefereeController refereeController = new RefereeController();
        Match match = new Match();
        ScoreController scoreController = new ScoreController();
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
                        match = new Match(totalSets, player1, player2);
                        scoreController = new ScoreController(match.getScoreBoard(), match.getSets().get(0).getCurrentGame());
                        matchController = new MatchController(matchView);

                        matchController.createMatch(totalSets, player1, player2);
                        matchView.displayInitialMatch(match);
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
//                    pointActions.get(command).accept(scoreController);
                    matchView.displayMatchScore();
                    break;

                case "displayScore":
                    if (matchController != null) {
                        matchView.displayMatchScore();
                    } else {
                        logger.info("No match is active.");
                    }
                    break;

                case "readMatch":
                    matchView.displayMatchResult();
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
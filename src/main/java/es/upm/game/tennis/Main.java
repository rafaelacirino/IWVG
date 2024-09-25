package es.upm.game.tennis;

import es.upm.game.tennis.controller.*;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;
import es.upm.game.tennis.view.MatchView;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
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

    public static void main(String[] args) {
        MatchView matchView = new MatchView();
        MatchController matchController = new MatchController();
        PlayerController playerController = new PlayerController();
        RefereeController refereeController = new RefereeController();

        boolean running = true;

        while (running) {
            matchView.promptCommand();
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
                    boolean success = refereeController.login(loginName, loginPassword);
                    matchView.displayLoginStatus(success);
                    break;

                case "createPlayer":
                    String playerName = getInput(ENTER_PLAYER_NAME);
                    Player player = playerController.createPlayer(playerName);
                    matchView.displayPlayerCreated(player);
                    break;

                case "readPlayers":
                    List<Player> players = playerController.getPlayers();
                    for (Player p : players) {
                        logger.info("Player ID: " + p.getId() + ", Name: " + p.getName());
                    }
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
                    int totalSets = getInputNumber(ENTER_TOTAL_SETS);
                    int id1 = getInputNumber(ENTER_PLAYER_ID);
                    int id2 = getInputNumber(ENTER_PLAYER_ID);

                    Optional<Player> playerId1 = playerController.getPlayerById(id1);
                    Optional<Player> playerId2 = playerController.getPlayerById(id2);

                    if (playerId1.isPresent() && playerId2.isPresent()) {
                        Player player1 = playerId1.get();
                        Player player2 = playerId2.get();
                        matchController.createMatch(totalSets, player1, player2);
                        matchView.displayInitialMatch(matchController);
                    } else {
                        logger.warning("One or both players not found.");
                    }
                    break;

                case "lackService":
                    matchController.addPointToReceiver();
                    matchView.displayMatchScore(matchController.getMatchScore());
                    break;

                case "pointService":
                    matchController.addPointToServer();
                    matchView.displayMatchScore(matchController.getMatchScore());
                    break;

                case "pointRest":
                    matchController.addPointToReceiver();
                    matchView.displayMatchScore(matchController.getMatchScore());
                    break;

                case "readMatch":
                    logger.info("Enter match ID: ");
                    int matchId = scanner.nextInt();
                    String matchScore = matchController.getMatchScore();
                    logger.info("Match Score: " + matchScore);
                    break;

                case "logout":
                    running = false;
                    break;

                default:
                    logger.warning("Unknown command.");
                    break;
            }
        }

        scanner.close();
    }
}
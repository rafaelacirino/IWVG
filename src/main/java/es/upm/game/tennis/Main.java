package es.upm.game.tennis;

import es.upm.game.tennis.controller.*;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;
import es.upm.game.tennis.view.MatchView;

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

    private static final Map<String, Consumer<MatchController>> pointActions = new HashMap<>();

    static {
        pointActions.put("lackService", MatchController::addPointToReceiver);
        pointActions.put("pointService", MatchController::addPointToServer);
        pointActions.put("pointRest", MatchController::addPointToReceiver);
    }

    public static void main(String[] args) {
        MatchView matchView = MatchView.getInstance();
        MatchController matchController = new MatchController(matchView);
        PlayerController playerController = new PlayerController(matchView);
        RefereeController refereeController = new RefereeController(matchView);

        boolean running = true;

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine();

            switch (command) {
                case "createReferee":
                    String refName = getInput(ENTER_REFEREE_NAME);
                    String refPassword = getInput(ENTER_REFEREE_PASSWORD);
                    Referee referee = refereeController.createReferee(refName, refPassword);
                    refereeController.getDisplayRefereeCreated(referee);
                    break;

                case "login":
                    String loginName = getInput(ENTER_REFEREE_NAME);
                    String loginPassword = getInput(ENTER_REFEREE_PASSWORD);
                    isLoggedIn = refereeController.login(loginName, loginPassword);
                    refereeController.getDisplayLoginStatus(isLoggedIn);
                    break;

                case "createPlayer":
                    String playerName = getInput(ENTER_PLAYER_NAME);
                    Player player = playerController.createPlayer(playerName);
                    playerController.getDisplayPlayerCreated(player);
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
                    if (!isLoggedIn) {
                        logger.warning("No referee logged in");
                        break;
                    }

                    int totalSets = getInputNumber(ENTER_TOTAL_SETS);
                    int id1 = getInputNumber(ENTER_PLAYER_ID);
                    int id2 = getInputNumber(ENTER_PLAYER_ID);

                    Optional<Player> playerId1 = playerController.getPlayerById(id1);
                    Optional<Player> playerId2 = playerController.getPlayerById(id2);

                    if (playerId1.isPresent() && playerId2.isPresent()) {
                        Player player1 = playerId1.get();
                        Player player2 = playerId2.get();
                        matchController.createMatch(totalSets, player1, player2);
                        matchController.getDisplayMatchInitial();
                    } else {
                        logger.warning("One or both players not found.");
                    }
                    break;

                case "lackService":
                case "pointService":
                case "pointRest":
                    if (!isMatchCreated) {
                        logger.warning("Must create a match before adding points");
                        break;
                    }
                    pointActions.get(command).accept(matchController);
                    matchController.getDisplayMatchScore();
                    break;

                case "readMatch":
                    matchController.getDisplayMatchResult();
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
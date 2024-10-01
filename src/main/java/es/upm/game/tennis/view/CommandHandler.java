package es.upm.game.tennis.view;

import es.upm.game.tennis.Main;
import es.upm.game.tennis.controller.MatchController;
import es.upm.game.tennis.controller.PlayerController;
import es.upm.game.tennis.controller.RefereeController;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class CommandHandler {

    private MatchController matchController;
    private final PlayerController playerController;
    private final RefereeController refereeController;
    private final MatchView matchView;
    private final PlayerView playerView;
    private final RefereeView refereeView;
    private boolean isMatchCreated = false;
    private boolean isLoggedIn = false;
    private boolean running = true;

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


    public CommandHandler(MatchController matchController, PlayerController playerController, RefereeController refereeController, MatchView matchView, PlayerView playerView, RefereeView refereeView) {
        this.matchController = matchController;
        this.playerController = playerController;
        this.refereeController = refereeController;
        this.matchView = matchView;
        this.playerView = playerView;
        this.refereeView = refereeView;
    }

    public void handleCommand(String command) {
            switch (command) {
                case "createReferee":
                    String refName = getInput(ENTER_REFEREE_NAME);
                    String refPassword = getInput(ENTER_REFEREE_PASSWORD);
                    Referee referee = refereeController.createReferee(refName, refPassword);
                    refereeView.displayRefereeCreated(referee);
                    break;

                case "login":
                    login();
                    break;

                case "createPlayer":
                    createPlayer();
                    break;

                case "readPlayer":
                    readPlayer();
                    break;

                case "readPlayers":
                    readPlayers();
                    break;

                case "createMatch":
                    createMatch();
                    break;

                case "lackService", "pointService", "pointRest":
                    handleMatchAction(command);
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

    private void login(){
        String loginName = getInput(ENTER_REFEREE_NAME);
        String loginPassword = getInput(ENTER_REFEREE_PASSWORD);
        isLoggedIn = refereeController.login(loginName, loginPassword);
        refereeView.displayLoginStatus(isLoggedIn);
    }

    private void createPlayer(){
        String playerName = getInput(ENTER_PLAYER_NAME);
        Player player = playerController.createPlayer(playerName);
        playerView.displayPlayerCreated(player);
    }

    private void readPlayer(){
        int playerId = getInputNumber(ENTER_PLAYER_ID);
        Optional<Player> foundPlayer = playerController.getPlayerById(playerId);

        foundPlayer.ifPresentOrElse(
                p -> logger.info("Player ID: " + p.getId() + ", Name: " + p.getName()),
                () -> logger.warning("Player not found.")
        );
    }

    private void readPlayers() {
        List<Player> players = playerController.getPlayers();
        for (Player p : players) {
            logger.info("Player ID: " + p.getId() + ", Name: " + p.getName());
        }
    }

    private void createMatch(){
        if (!isLoggedIn) {
            Logger.getLogger(CommandHandler.class.getName()).warning("No referee logged in");
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
            Logger.getLogger(CommandHandler.class.getName()).warning("One or both players not found.");
        }
    }

    private void handleMatchAction(String command) {
        if (!isMatchCreated) {
            Logger.getLogger(CommandHandler.class.getName()).warning("Must create a match before adding points");
            return;
        }
        if ("lackService".equals(command)) {
            matchController.lackService();
        } else if ("pointService".equals(command)) {
            matchController.pointService();
        } else if ("pointRest".equals(command)) {
            matchController.pointRest();
        }
        matchView.displayMatchScore(matchController);
    }
}
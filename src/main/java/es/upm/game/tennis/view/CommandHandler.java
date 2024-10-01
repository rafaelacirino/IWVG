package es.upm.game.tennis.view;

import es.upm.game.tennis.controller.CommandController;
import es.upm.game.tennis.utils.ConstantsUtil;
import es.upm.game.tennis.controller.MatchController;
import es.upm.game.tennis.controller.PlayerController;
import es.upm.game.tennis.controller.RefereeController;
import es.upm.game.tennis.model.Referee;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class CommandHandler {

    private MatchController matchController;
    private final RefereeController refereeController;
    private final MatchView matchView;
    private final RefereeView refereeView;
    private final CommandController commandController;
//    private boolean isMatchCreated = false;
//    private boolean isLoggedIn = false;
    private boolean running = true;

//    private static final Scanner scanner = new Scanner(System.in);

    private static final Map<String, Consumer<MatchController>> pointActions = new HashMap<>();

    static {
        pointActions.put("lackService", MatchController::lackService);
        pointActions.put("pointService", MatchController::pointService);
        pointActions.put("pointRest", MatchController::pointRest);
    }


    public CommandHandler(MatchController matchController, RefereeController refereeController, MatchView matchView,
                          RefereeView refereeView, CommandController commandController) {
        this.matchController = matchController;
        this.refereeController = refereeController;
        this.matchView = matchView;
        this.refereeView = refereeView;
        this.commandController = commandController;
    }

    public void handleCommand(String command) {
        switch (command) {
                case "createReferee":
                    String refName = commandController.getInput(ConstantsUtil.REFEREE_NAME);
                    String refPassword = commandController.getInput(ConstantsUtil.REFEREE_PASSWORD);
                    Referee referee = refereeController.createReferee(refName, refPassword);
                    refereeView.displayRefereeCreated(referee);
                    break;

                case "login":
                    commandController.login();
                    break;

                case "createPlayer":
                    commandController.createPlayer();
                    break;

                case "readPlayer":
                    commandController.readPlayer();
                    break;

                case "readPlayers":
                    commandController.readPlayers();
                    break;

                case "createMatch":
                    commandController.createMatch();
                    break;

                case "lackService", "pointService", "pointRest":
                    commandController.handleMatchAction(command);
                    break;

                case "readMatch":
                    matchView.displayMatchResult(matchController);
                    break;

                case "logout":
                    running = false;
                    Logger.getLogger(CommandHandler.class.getName()).info("Exiting...");
                    break;

                default:
                    Logger.getLogger(CommandHandler.class.getName()).warning("Unknown command.");
                    break;
            }
    }

//    private static String getInput(String promptMessage) {
//        Logger.getLogger(CommandHandler.class.getName()).info(promptMessage);
//        return scanner.nextLine();
//    }
//
//    private static int getInputNumber(String promptMessage) {
//        Logger.getLogger(CommandHandler.class.getName()).info(promptMessage);
//        int number = scanner.nextInt();
//        scanner.nextLine();
//        return number;
//    }
//
//    private void login(){
//        String loginName = getInput(ConstantsUtil.REFEREE_NAME);
//        String loginPassword = getInput(ConstantsUtil.REFEREE_PASSWORD);
//        isLoggedIn = refereeController.login(loginName, loginPassword);
//        refereeView.displayLoginStatus(isLoggedIn);
//    }
//
//    private void createPlayer(){
//        String playerName = getInput(ConstantsUtil.PLAYER_NAME);
//        Player player = playerController.createPlayer(playerName);
//        playerView.displayPlayerCreated(player);
//    }
//
//    private void readPlayer(){
//        int playerId = getInputNumber(ConstantsUtil.PLAYER_ID);
//        Optional<Player> foundPlayer = playerController.getPlayerById(playerId);
//
//        foundPlayer.ifPresentOrElse(
//                p -> Logger.getLogger(CommandHandler.class.getName()).info("Player ID: " + p.getId() + ", Name: " + p.getName()),
//                () -> Logger.getLogger(CommandHandler.class.getName()).warning("Player not found.")
//        );
//    }
//
//    private void readPlayers() {
//        List<Player> players = playerController.getPlayers();
//        for (Player p : players) {
//            Logger.getLogger(CommandHandler.class.getName()).info("Player ID: " + p.getId() + ", Name: " + p.getName());
//        }
//    }
//
//    private void createMatch(){
//        if (!isLoggedIn) {
//            Logger.getLogger(CommandHandler.class.getName()).warning("No referee logged in");
//        }
//
//        int totalSets = getInputNumber(ConstantsUtil.TOTAL_SETS);
//        int id1 = getInputNumber(ConstantsUtil.PLAYER_ID);
//        int id2 = getInputNumber(ConstantsUtil.PLAYER_ID);
//
//        Player player1 = playerController.getPlayerById(id1).orElse(null);
//        Player player2 = playerController.getPlayerById(id2).orElse(null);
//
//        if (player1 != null && player2 != null) {
//            matchController = new MatchController(matchView);
//
//            matchController.createMatch(totalSets, player1, player2);
//            matchView.displayInitialMatch(matchController);
//            isMatchCreated = true;
//        } else {
//            Logger.getLogger(CommandHandler.class.getName()).warning("One or both players not found.");
//        }
//    }
//
//    private void handleMatchAction(String command) {
//        if (!isMatchCreated) {
//            Logger.getLogger(CommandHandler.class.getName()).warning("Must create a match before adding points");
//            return;
//        }
//        if ("lackService".equals(command)) {
//            matchController.lackService();
//        } else if ("pointService".equals(command)) {
//            matchController.pointService();
//        } else if ("pointRest".equals(command)) {
//            matchController.pointRest();
//        }
//        matchView.displayMatchScore(matchController);
//    }
}
package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.utils.ConstantsUtil;
import es.upm.game.tennis.view.CommandHandler;
import es.upm.game.tennis.view.MatchView;
import es.upm.game.tennis.view.PlayerView;
import es.upm.game.tennis.view.RefereeView;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

public class CommandController {

    private MatchController matchController;
    private final MatchView matchView;
    private final RefereeController refereeController;
    private final PlayerController playerController;

    private boolean isMatchCreated = false;
    private boolean isLoggedIn = false;
    private final Scanner scanner = new Scanner(System.in);

    public CommandController(MatchView matchView, RefereeController refereeController, PlayerController playerController) {
        this.matchView = matchView;
        this.refereeController = refereeController;
        this.playerController = playerController;
    }

    public CommandController(RefereeController refereeController, MatchView matchView,
                             RefereeController refereeController1, PlayerController playerController) {
        this.matchView = matchView;
        this.refereeController = refereeController1;
        this.playerController = playerController;
    }


    public String getInput(String promptMessage) {
        Logger.getLogger(CommandHandler.class.getName()).info(promptMessage);
        return scanner.nextLine();
    }

    public int getInputNumber(String promptMessage) {
        Logger.getLogger(CommandHandler.class.getName()).info(promptMessage);
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public void login(){
        RefereeView refereeView = new RefereeView();
        String loginName = getInput(ConstantsUtil.REFEREE_NAME);
        String loginPassword = getInput(ConstantsUtil.REFEREE_PASSWORD);
        isLoggedIn = refereeController.login(loginName, loginPassword);
        refereeView.displayLoginStatus(isLoggedIn);
    }

    public void createPlayer(){
        PlayerView playerView = new PlayerView();
        String playerName = getInput(ConstantsUtil.PLAYER_NAME);
        playerView.displayPlayerCreated(playerController.createPlayer(playerName));
    }

    public void readPlayer(){
        int playerId = getInputNumber(ConstantsUtil.PLAYER_ID);
        Optional<Player> foundPlayer = playerController.getPlayerById(playerId);

        foundPlayer.ifPresentOrElse(
                p -> Logger.getLogger(CommandHandler.class.getName()).info("Player ID: " + p.getId() + ", Name: " + p.getName()),
                () -> Logger.getLogger(CommandHandler.class.getName()).warning("Player not found.")
        );
    }

    public void readPlayers() {
        List<Player> players = playerController.getPlayers();
        for (Player p : players) {
            Logger.getLogger(CommandHandler.class.getName()).info("Player ID: " + p.getId() + ", Name: " + p.getName());
        }
    }

    public void createMatch(){
        if (!isLoggedIn) {
            Logger.getLogger(CommandHandler.class.getName()).warning("No referee logged in");
            return;
        }

        int totalSets = getInputNumber(ConstantsUtil.TOTAL_SETS);
        int id1 = getInputNumber(ConstantsUtil.PLAYER_ID);
        int id2 = getInputNumber(ConstantsUtil.PLAYER_ID);

        Player player1 = playerController.getPlayerById(id1).orElse(null);
        Player player2 = playerController.getPlayerById(id2).orElse(null);

        if (player1 != null && player2 != null) {
            MatchController matchController = new MatchController(matchView);

            matchController.createMatch(totalSets, player1, player2);
            matchView.displayInitialMatch(matchController);
            isMatchCreated = true;
            //return;
        } else {
            Logger.getLogger(CommandHandler.class.getName()).warning("One or both players not found.");
        }
    }

    public void handleMatchAction(String command) {
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
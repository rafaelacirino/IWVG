package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;
import es.upm.game.tennis.utils.ConstantsUtil;
import es.upm.game.tennis.view.MatchView;
import es.upm.game.tennis.view.PlayerView;
import es.upm.game.tennis.view.RefereeView;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

public class CommandController {

    private final MatchController matchController;
    private final MatchView matchView;
    private final RefereeController refereeController;
    private final PlayerController playerController;

    private boolean isMatchCreated = false;
    private boolean isLoggedIn = false;
    private final Scanner scanner = new Scanner(System.in);

    public CommandController(MatchController matchController, MatchView matchView, RefereeController refereeController,
                             PlayerController playerController) {
        this.matchController = matchController;
        this.matchView = matchView;
        this.refereeController = refereeController;
        this.playerController = playerController;
    }

    public String getInput(String promptMessage) {
        Logger.getLogger(CommandController.class.getName()).info(promptMessage);
        return scanner.nextLine();
    }

    public int getInputNumber(String promptMessage) {
        Logger.getLogger(CommandController.class.getName()).info(promptMessage);
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public void createReferee() {
        RefereeView refereeView = new RefereeView();
        String refName = getInput(ConstantsUtil.REFEREE_NAME);
        String refPassword = getInput(ConstantsUtil.REFEREE_PASSWORD);
        Referee referee = refereeController.createReferee(refName, refPassword);
        refereeView.displayRefereeCreated(referee);
    }

    public void login() {
        RefereeView refereeView = new RefereeView();
        String loginName = getInput(ConstantsUtil.REFEREE_NAME);
        String loginPassword = getInput(ConstantsUtil.REFEREE_PASSWORD);
        isLoggedIn = refereeController.login(loginName, loginPassword);
        refereeView.displayLoginStatus(isLoggedIn);
    }

    public void createPlayer() {
        PlayerView playerView = new PlayerView();
        String playerName = getInput(ConstantsUtil.PLAYER_NAME);
        playerView.displayPlayerCreated(playerController.createPlayer(playerName));
    }

    public void readPlayer() {
        int playerId = getInputNumber(ConstantsUtil.PLAYER_ID);
        Optional<Player> foundPlayer = playerController.getPlayerById(playerId);

        foundPlayer.ifPresentOrElse(
                p -> Logger.getLogger(CommandController.class.getName()).info("Player ID: " + p.getId() + ", Name: " + p.getName()),
                () -> Logger.getLogger(CommandController.class.getName()).warning(ConstantsUtil.PLAYER_NOT_FOUND)
        );
    }

    public void readPlayers() {
        List<Player> players = playerController.getPlayers();
        for (Player p : players) {
            Logger.getLogger(CommandController.class.getName()).info("Player ID: " + p.getId() + ", Name: " + p.getName());
        }
    }

    public void createMatch() {
        if (!isLoggedIn) {
            Logger.getLogger(CommandController.class.getName()).warning(ConstantsUtil.NO_REFEREE_LOGGED_IN);
            return;
        }

        int totalSets = getInputNumber(ConstantsUtil.TOTAL_SETS);
        int idPlayerService = getInputNumber(ConstantsUtil.PLAYER_ID);
        int idPlayerRest = getInputNumber(ConstantsUtil.PLAYER_ID);

        Player playerService = playerController.getPlayerById(idPlayerService).orElse(null);
        Player playerRest = playerController.getPlayerById(idPlayerRest).orElse(null);

        if (playerService != null && playerRest != null) {
            matchController.createMatch(totalSets, playerService, playerRest);
            matchView.displayInitialMatch(matchController);
            isMatchCreated = true;
        } else {
            Logger.getLogger(CommandController.class.getName()).warning(ConstantsUtil.PLAYERS_NOT_FOUND);
        }
    }

    public void handleMatchAction(String command) {
        if (!isMatchCreated) {
            Logger.getLogger(CommandController.class.getName()).warning(ConstantsUtil.MATCH_BEFORE_ADDING_POINTS);
            return;
        }
        switch (command) {
            case "lackService" -> matchController.lackService();
            case "pointService" -> matchController.pointService();
            case "pointRest" -> matchController.pointRest();
            default -> System.out.println("Unknown command: " + command);
        }
        matchView.displayMatchScore(matchController);
    }
}
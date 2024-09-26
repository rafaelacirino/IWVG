package es.upm.game.tennis.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Match {

    private Date date;
    private Player playerService;
    private Player playerRest;
    private boolean isPlayerService;
    private Game currentGame;

    public Match() {
    }

    public Match(int totalSets, Player playerService, Player playerRest) {
        this.playerService = playerService;
        this.playerRest = playerRest;
        this.date = new Date();
        startNewGame();
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void startNewGame() {
        this.currentGame = new Game(playerService, playerRest);
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public Player getPlayerService() {
        return playerService;
    }

    public Player getPlayerRest() {
        return playerRest;
    }

    public String getMatchScore() {
        String currentServer = isPlayerService ? "*" : " ";
        String currentReceiver = !isPlayerService ? "*" : " ";


        StringBuilder scoreBuilder = new StringBuilder();
        scoreBuilder.append(String.format("%s %s: %s%n", currentServer, playerService.getName(), playerService.getCurrentPoints()))
                .append(String.format("%s %s: %s%n", currentReceiver, playerRest.getName(), playerRest.getCurrentPoints()));

        return scoreBuilder.toString();
    }

    public void switchRoles() {
        isPlayerService = !isPlayerService;
        Player temp = playerService;
        playerService = playerRest;
        playerRest = temp;
    }
}
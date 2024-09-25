package es.upm.game.tennis.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Match {

    private int id;
    private Date date;
    private int totalSets;
    private ArrayList<Set> setsPlayed;
    private Player playerService;
    private Player playerRest;
    private Game currentGame;

    public Match() {
    }

    public Match(int totalSets, Player playerService, Player playerRest) {
        this.totalSets = totalSets;
        this.playerService = playerService;
        this.playerRest = playerRest;
        this.setsPlayed = new ArrayList<>();
        this.date = new Date();
        startNewGame();
   }

    public void startNewGame() {
        this.currentGame = new Game(playerService, playerRest);
    }

    public Game getCurrentGame() {
        return this.currentGame;
    }

    public int getId() {
        return id;
    }

    public String getDateFormatted() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public Player getPlayerService() {
        return playerService;
    }

    public Player getPlayerRest() {
        return playerRest;
    }

    public boolean isMatchOver() {
        return setsPlayed.size() >= totalSets && setsPlayed.get(setsPlayed.size() - 1).isSetOver();
    }

    public String getMatchScore() {

        return String.format("id: %d %n", getId()) +
                String.format("date: %s %n", getDateFormatted()) +
                String.format("* %s: %s - - - %n",
                        playerService.getName(), playerService.getCurrentPoints()) +
                String.format("%s: %s - - - %n",
                        playerRest.getName(), playerRest.getCurrentPoints());
    }

    public void switchRoles() {
        Player temp = playerService;
        playerService = playerRest;
        playerRest = temp;
    }
}
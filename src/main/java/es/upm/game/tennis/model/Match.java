package es.upm.game.tennis.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {

    private int id;
    private Date date;
    private int totalSets;
    private ArrayList<Set> setsPlayed;
    private Player playerService;
    private Player playerRest;
    private boolean isPlayerService;
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

    public int getId() {
        return id;
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

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(playerService);
        players.add(playerRest);
        return players;
    }

    public List<Set> getSets() {
        return setsPlayed;
    }

    public void addSet(Set set) {
        setsPlayed.add(set);
    }

    public boolean isMatchOver() {
        return setsPlayed.size() >= totalSets && setsPlayed.get(setsPlayed.size() - 1).isSetOver();
    }

    public String getMatchScore() {
        String currentServer = isPlayerService ? "*" : " ";
        String currentReceiver = !isPlayerService ? "*" : " ";


        StringBuilder scoreBuilder = new StringBuilder();
        scoreBuilder.append(String.format("%s %s: %s%n", currentServer, playerService.getName(), playerService.getCurrentPoints()))
                .append(String.format("%s %s: %s%n", currentReceiver, playerRest.getName(), playerRest.getCurrentPoints()));

        return String.format("id: %d %n", getId()) +
                String.format("date: %s %n", getDate()) +
                String.format("* %s: %s - - - %n",
                        playerService.getName(), playerService.getCurrentPoints()) +
                String.format("%s: %s - - - %n",
                        playerRest.getName(), playerRest.getCurrentPoints());
    }

    public void switchRoles() {
        isPlayerService = !isPlayerService;
        Player temp = playerService;
        playerService = playerRest;
        playerRest = temp;
    }
}
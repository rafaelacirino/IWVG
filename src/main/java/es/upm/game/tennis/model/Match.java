package es.upm.game.tennis.model;

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

    public Match(int totalSets, Player playerService, Player playerRest) {
        this.totalSets = totalSets;
        this.playerService = playerService;
        this.playerRest = playerRest;
        this.setsPlayed = new ArrayList<>();
        this.date = new Date(); // Data do início da partida
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
        StringBuilder score = new StringBuilder();
        for (Set set : setsPlayed) {
            score.append(set.getSetScore()).append("\n");
        }
        return score.toString();
    }
}
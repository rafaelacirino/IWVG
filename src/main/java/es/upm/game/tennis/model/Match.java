package es.upm.game.tennis.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Match {

    private String id;
    private Date date;
    private List<Set> sets;
    private int totalSets;
    private Set currentSet;
    private ScoreBoard scoreBoard;

    public Match() {
    }

    public Match(int totalSets, Player playerService, Player playerRest) {
        assert totalSets == 3 || totalSets == 5 : "Total sets must be 3 or 5";
        this.totalSets = totalSets;
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.sets = new ArrayList<>(totalSets);
        this.scoreBoard = new ScoreBoard(totalSets, playerService, playerRest);

        for (int i = 0; i < totalSets; i++) {
            this.sets.add(new Set(playerService, playerRest));
        }
        currentSet = sets.get(0);
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public List<Set> getSets() {
        return sets;
    }

    public int getTotalSets() {
        return totalSets;
    }

    public Set getCurrentSet() {
        return currentSet;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

}
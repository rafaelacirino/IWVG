package es.upm.game.tennis.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {

    private Date date;
    private List<Set> sets;
    private int totalSets;
    private Set currentSet;
    private ScoreBoard scoreBoard;

    public Match() {
    }

    public Match(int totalSets, Player playerService, Player playerRest) {
        if (totalSets != 3 && totalSets != 5) {
            System.err.println("Error: total sets must be 3 or 5. Defaulting to 3");
            totalSets = 3;
        }
        this.totalSets = totalSets;
        this.date = new Date();
        this.sets = new ArrayList<>(totalSets);
        this.scoreBoard = new ScoreBoard(totalSets, playerService, playerRest);

        for (int i = 0; i < totalSets; i++) {
            this.sets.add(new Set(playerService, playerRest));
        }
        currentSet = sets.get(0);
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public Set getCurrentSet() {
        return currentSet;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
package es.upm.game.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class MatchScore {
    private final int totalSets;
    private final List<SetScore> setScores = new ArrayList<>();
    private final int[] setsWon = {0, 0};

    public MatchScore(int totalSets) {
        this.totalSets = totalSets;
        setScores.add(new SetScore());
    }

    public void updateGameScore(int playerIndex) {
        SetScore currentSet = getCurrentSet();
        currentSet.addGame(playerIndex);

        if (currentSet.isSetOver()) {
            setsWon[playerIndex]++;
            setScores.add(new SetScore());
        }
    }

    public boolean isMatchOver() {
        return setsWon[0] > totalSets / 2 || setsWon[1] > totalSets / 2;
    }

    public SetScore getCurrentSet() {
        return setScores.get(setsWon[0] + setsWon[1]);
    }

    public int[] getSetsWon() {
        return setsWon;
    }
}

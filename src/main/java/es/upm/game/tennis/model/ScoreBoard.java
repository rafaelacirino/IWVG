package es.upm.game.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final int[] currentPoints;
    private final int[][] currentGames;
    private final int[] currentSets;
    private int lackServiceCount;
    private final int totalSets;
    private final List<Player> players;

    public ScoreBoard(int totalSets, Player playerService, Player playerRest) {
        this.totalSets = totalSets;
        this.players = new ArrayList<>();
        this.players.add(playerService);
        this.players.add(playerRest);
        this.currentPoints = new int[]{0, 0};
        this.currentGames = new int[totalSets][2];
        this.currentSets = new int[]{0, 0};
        this.lackServiceCount = 0;
    }

    public int[] getCurrentPoints() {
        return currentPoints;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void incrementServiceFault() {
        lackServiceCount++;
    }

    public int getServiceFaultCount() {
        return lackServiceCount;
    }

    public void resetServiceFaultCount() {
        lackServiceCount = 0;
    }

    public void updatePoints(Player player) {
        int playerIndex = players.indexOf(player);
        currentPoints[playerIndex] += 1;

        if (isGameOver()) {
            updateGames(player);
            resetPoints();
        }
    }

    public void updateGames(Player player) {
        int playerIndex = players.indexOf(player);
        int currentSetIndex = getCurrentSetIndex();

        currentGames[currentSetIndex][playerIndex]++;
        if (currentGames[currentSetIndex][playerIndex] >= 6 &&
                (currentGames[currentSetIndex][playerIndex] - currentGames[currentSetIndex][1 - playerIndex]) >= 2) {
            updateSets(player);
            resetGames();
        }
    }

    public void updateSets(Player player) {
        int playerIndex = players.indexOf(player);
        currentSets[playerIndex]++;
    }

    public void resetPoints() {
        currentPoints[0] = 0;
        currentPoints[1] = 0;
    }

    public void resetGames() {
        int currentSetIndex = getCurrentSetIndex();

        if (currentSetIndex < totalSets) {
            currentGames[currentSetIndex][0] = 0;
            currentGames[currentSetIndex][1] = 0;
        }
    }

    public boolean isGameOver() {
        return Math.max(currentPoints[0], currentPoints[1]) >= 4 &&
                Math.abs(currentPoints[0] - currentPoints[1]) >= 2;
    }

    public boolean isSetOver() {
        int currentSetIndex = getCurrentSetIndex();
        return Math.max(currentGames[currentSetIndex][0], currentGames[currentSetIndex][1]) >= 6 &&
                Math.abs(currentGames[currentSetIndex][0] - currentGames[currentSetIndex][1]) >= 2;
    }

    private int getCurrentSetIndex() {
        return currentSets[0] + currentSets[1];
    }

}

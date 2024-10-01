package es.upm.game.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private final GameScore gameScore;
    private final MatchScore matchScore;
    private final FaultScore faultScore;
    private final List<Player> players;

    public ScoreBoard(int totalSets, Player playerService, Player playerRest) {
        this.players = new ArrayList<>();
        this.players.add(playerService);
        this.players.add(playerRest);

        this.gameScore = new GameScore();
        this.matchScore = new MatchScore(totalSets);
        this.faultScore = new FaultScore();
    }

    public MatchScore getMatchScore() {
        return matchScore;
    }

    public void addPoint(Player player) {
        int playerIndex = players.indexOf(player);
        gameScore.addPoint(playerIndex);

        if (gameScore.isGameOver()) {
            matchScore.updateGameScore(playerIndex);
            gameScore.resetPoints();
        }
    }

    public void incrementServiceFault() {
        faultScore.incrementFault();
    }

    public int getServiceFaultCount() {
        return faultScore.getFaultCount();
    }

    public void resetServiceFaultCount() {
        faultScore.resetFaultCount();
    }

    public int[] getCurrentPoints() {
        return gameScore.getPoints();
    }

    public int[] getGamesInCurrentSet() {
        return matchScore.getCurrentSet().getGamesWon();
    }

    public int[] getSetsWon() {
        return matchScore.getSetsWon();
    }

    public boolean isMatchOver() {
        return matchScore.isMatchOver();
    }
}

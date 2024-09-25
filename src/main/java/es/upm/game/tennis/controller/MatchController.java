package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;

public class MatchController {

    private Match match;
    private Game currentGame;
    private TieBreak currentTieBreak;

    public MatchController() {
    }

    public MatchController(Match match) {
        this.match = match;
        startNewGame();
    }

    public void createMatch(int totalSets, Player player1, Player player2) {
        Player playerService = Math.random() < 0.5 ? player1 : player2;
        Player playerRest = playerService == player1 ? player2 : player1;
        match = new Match(totalSets, playerService, playerRest);
    }

    public void startNewGame() {
        this.currentGame = new Game();
    }

    public void startTieBreak() {
        this.currentTieBreak = new TieBreak(match.getPlayers().get(0), match.getPlayers().get(1));
    }

    public void addPointToServer() {
        currentGame.addPoint(match.getPlayers().get(0));
        checkGameEndAndSwitchRoles();
    }

    public void addPointToReceiver() {
        currentGame.addPoint(match.getPlayers().get(1));
        checkGameEndAndSwitchRoles();
    }

    private void checkGameEndAndSwitchRoles() {
        if (currentGame.isGameOver()) {
            match.switchRoles();
            startNewGame();
        }
    }

    public boolean isMatchOver() {
        return match.isMatchOver();
    }

    public String getMatchScore() {
        return match.getMatchScore();
    }

    public void addSetToMatch(Set set) {
        match.addSet(set);
    }
}
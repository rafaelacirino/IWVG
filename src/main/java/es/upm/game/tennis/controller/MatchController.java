package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.view.MatchView;

public class MatchController {

    private Match match;
    private Game currentGame;
    private TieBreak currentTieBreak;
    private int idNumber = 0;
    private MatchView matchView;

    public MatchController(MatchView matchView) {
        this.matchView = matchView;
    }

    public MatchController(Match match, MatchView matchView) {
        this.match = match;
        this.matchView = matchView;
        startNewGame();
    }

    public void createMatch(int totalSets, Player player1, Player player2) {
        Player playerService = Math.random() < 0.5 ? player1 : player2;
        Player playerRest = playerService == player1 ? player2 : player1;
        match = new Match(idNumber++, totalSets, playerService, playerRest);
        matchView.setMatch(match);
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

    public void getDisplayMatchInitial() {
        matchView.displayMatchInitial();
    }

    public void getDisplayMatchScore() {
        matchView.displayMatchScore();
    }

    public void getDisplayMatchResult() {
        matchView.displayMatchResult();
    }
}
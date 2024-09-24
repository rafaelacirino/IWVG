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
        startNewGame(); // Começa o primeiro game automaticamente
    }

    public void createMatch(int totalSets, Player playerService, Player playerRest) {
        match = new Match(totalSets, playerService, playerRest);
    }

    public void startNewGame() {
        this.currentGame = new Game();
    }

    public void startTieBreak() {
        this.currentTieBreak = new TieBreak(match.getPlayers().get(0), match.getPlayers().get(1));
    }

    public void addPointToServer() {
        currentGame.addPoint(match.getPlayers().get(0)); // Adiciona ponto ao jogador que serve
    }

    public void addPointToReceiver() {
        currentGame.addPoint(match.getPlayers().get(1)); // Adiciona ponto ao jogador que recebe
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
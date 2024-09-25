package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.service.MatchService;
import es.upm.game.tennis.view.MatchView;

public class MatchController {

    private final MatchService matchService;
    private final MatchView matchView;

    public MatchController(Match match, MatchView matchView, MatchService matchService) {
        this.matchService = new MatchService(match);
        this.matchView = matchView;
    }

    public void createMatch(int totalSets, Player playerService, Player playerRest) {
        matchService.createMatch(totalSets, playerService, playerRest);
        matchView.displayInitialMatch(matchService.getMatchScore());
    }

    public void startMatch() {
        String player1Name = matchView.readPlayerName(1);
        int player1Id = matchView.readPlayerId(1);
        String player2Name = matchView.readPlayerName(2);
        int player2Id = matchView.readPlayerId(2);

        Player player1 = new Player(player1Id, player1Name);
        Player player2 = new Player(player2Id, player2Name);

        int totalSets = matchView.readTotalSets();

        matchService.createMatch(totalSets, player1, player2);
        matchView.displayMatchStarted(player1, player2);
    }

    public void pointToServer() {
        matchService.pointService();
        matchView.displayPointToServer(matchService.getCurrentServer());
        if (matchService.isGameOver()) {
            matchView.displayGameOver();
        }
    }

    public void pointToReceiver() {
        matchService.pointRest();
        matchView.displayPointToReceiver(matchService.getCurrentReceiver());
        if (matchService.isGameOver()) {
            matchView.displayGameOver();
        }
    }

    public void displayScore() {
        String score = matchService.getMatchScore();
        matchView.displayMatchScore(score);
    }

    public void readPlayers() {
        Player server = matchService.getCurrentServer();
        Player receiver = matchService.getCurrentReceiver();
        matchView.displayPlayers(server, receiver);
    }

    public void lackService() {
        matchService.lackService();
        matchView.displayLackService(matchService.getCurrentServer());
        if (matchService.isGameOver()) {
            matchView.displayGameOver();
        }
    }

    public void pointService() {
        matchService.pointService();
        matchView.displayPointToServer(matchService.getCurrentServer());
        if (matchService.isGameOver()) {
            matchView.displayGameOver();
        }
    }

    public void pointRest() {
        matchService.pointRest();
        matchView.displayPointToReceiver(matchService.getCurrentReceiver());
        if (matchService.isGameOver()) {
            matchView.displayGameOver();
        }
    }
}
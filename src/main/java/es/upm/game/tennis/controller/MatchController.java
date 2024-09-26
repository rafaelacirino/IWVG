package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.view.MatchView;

public class MatchController {

    private final ScoreController scoreController;
    private final MatchView matchView;
    private Match match;

    public MatchController(Match match, MatchView matchView) {
        this.scoreController = new ScoreController(match);
        this.matchView = matchView;
    }

    public void createMatch(int totalSets, Player playerCurrent, Player playerPast){
        Player playerService = Math.random() < 0.5 ? playerCurrent : playerPast;
        Player playerRest = (playerService == playerCurrent) ? playerPast : playerService;
        match = new Match(totalSets, playerService, playerRest);
    }

    public void getInitialMatch() {
        matchView.displayInitialMatch(scoreController);
    }

    public void getDisplayMatchScore() {
        matchView.displayMatchScore(scoreController);
    }

    public void lackService() {
        scoreController.lackService();
        matchView.displayLackService(scoreController.getCurrentServer());
        if (scoreController.isGameOver()) {
            matchView.displayGameOver();
        }
    }

    public void pointService() {
        scoreController.pointService();
        matchView.displayPointToServer(scoreController.getPlayerService());
        if (scoreController.isGameOver()) {
            matchView.displayGameOver();
        }
    }

    public void pointRest() {
        scoreController.pointRest();
        matchView.displayPointToReceiver(scoreController.getPlayerRest());
        if (scoreController.isGameOver()) {
            matchView.displayGameOver();
        }
    }
}
package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.view.MatchView;
import es.upm.game.tennis.view.ScoreView;

public class MatchController {

    private final ScoreController scoreController;
    private final MatchView matchView;
    private final ScoreView scoreView;
    private Match match;

    public MatchController(Match match, MatchView matchView, ScoreView scoreView) {
        this.scoreController = new ScoreController(match);
        this.matchView = matchView;
        this.scoreView = scoreView;
    }

    public void createMatch(int totalSets, Player playerCurrent, Player playerPast){
        Player playerService = Math.random() < 0.5 ? playerCurrent : playerPast;
        Player playerRest = (playerService == playerCurrent) ? playerPast : playerService;
        match = new Match(totalSets, playerService, playerRest);

        scoreController.setPlayerService(playerService);
        scoreController.setCurrentServer(playerService);
    }

    public void getInitialMatch() {
        matchView.displayInitialMatch(scoreController);
    }

    public void getDisplayMatchScore() {
        matchView.displayMatchScore(scoreController);
    }

    public void lackService() {
        scoreController.lackService();
        scoreView.displayLackService(scoreController.getCurrentServer());
        if (scoreController.isGameOver()) {
            scoreView.displayGameOver();
        }
    }

    public void pointService() {
        scoreController.pointService();
        scoreView.displayPointToServer(scoreController.getPlayerService());
        if (scoreController.isGameOver()) {
            scoreView.displayGameOver();
        }
    }

    public void pointRest() {
        scoreController.pointRest();
        scoreView.displayPointToReceiver(scoreController.getPlayerRest());
        if (scoreController.isGameOver()) {
            scoreView.displayGameOver();
        }
    }
}
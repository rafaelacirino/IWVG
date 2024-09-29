package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.view.MatchView;

public class MatchController {

    private Match match;
    private MatchView matchView;
    private ScoreController scoreController;

    public MatchController(MatchView matchView) {
        this.matchView = matchView;
    }

    public void createMatch(int totalSets, Player playerCurrent, Player playerPast){
        Player playerService = Math.random() < 0.5 ? playerCurrent : playerPast;
        Player playerRest = (playerService == playerCurrent) ? playerPast : playerService;
        match = new Match(totalSets, playerService, playerRest);

        scoreController = new ScoreController(match.getScoreBoard(), match.getCurrentSet().getCurrentGame());
    }

    public void pointService() {
        assert scoreController != null : "No match is currently active";

        scoreController.pointService();
    }

    public void pointRest() {
        assert scoreController != null : "No match is currently active";

        scoreController.pointRest();
    }


    public void lackService() {
        assert scoreController != null : "No match is currently active";

        scoreController.lackService();
    }
//
//    public void pointService() {
//        scoreController.pointService();
//        if (scoreController.isGameOver()) {
//            matchView.displayGameOver();
//        }
//    }
//
//    public void pointRest() {
//        scoreController.pointRest();
//        if (scoreController.isGameOver()) {
//            matchView.displayGameOver();
//        }
//    }


}
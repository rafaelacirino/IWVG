package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.view.MatchView;

public class MatchController {

    private static final String NO_MATCH_ACTIVE = "No match is currently active";
    private Match match;
    private ScoreController scoreController;

    public MatchController(MatchView matchView) {
    }

    public void createMatch(int totalSets, Player playerCurrent, Player playerPast){
        Player playerService = Math.random() < 0.5 ? playerCurrent : playerPast;
        Player playerRest = (playerService == playerCurrent) ? playerPast : playerService;
        match = new Match(totalSets, playerService, playerRest);

        scoreController = new ScoreController(match.getScoreBoard(), match.getCurrentSet().getCurrentGame());
    }

    public Match getMatch() {
        return match;
    }

    public void pointService() {
        assert scoreController != null : NO_MATCH_ACTIVE;

        scoreController.pointService();
    }

    public void pointRest() {
        assert scoreController != null : NO_MATCH_ACTIVE;

        scoreController.pointRest();
    }


    public void lackService() {
        assert scoreController != null : NO_MATCH_ACTIVE;

        scoreController.lackService();
    }

}
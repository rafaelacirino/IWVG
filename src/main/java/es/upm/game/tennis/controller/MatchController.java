package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.utils.ConstantsUtil;

public class MatchController {

    private Match match;
    private ScoreController scoreController;

    public MatchController() {
    }

    public void createMatch(int totalSets, Player playerCurrent, Player playerPast){
        Player playerService = Math.random() < 0.5 ? playerCurrent : playerPast;
        Player playerRest = (playerService == playerCurrent) ? playerPast : playerService;
        match = new Match(totalSets, playerService, playerRest);
        scoreController = new ScoreController(match.getScoreBoard().getMatchScore(), match.getCurrentSet().getCurrentGame(), match.getScoreBoard());
    }

    public Match getMatch() {
        return match;
    }

    public void pointService() {
        assert scoreController != null : ConstantsUtil.NO_MATCH_ACTIVE;
        scoreController.pointService();
    }

    public void pointRest() {
        assert scoreController != null : ConstantsUtil.NO_MATCH_ACTIVE;
        scoreController.pointRest();
    }

    public void lackService() {
        assert scoreController != null : ConstantsUtil.NO_MATCH_ACTIVE;
        scoreController.lackService();
    }
}
package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.utils.ConstantsUtil;

import java.util.logging.Logger;

public class ScoreController {

    private final ScoreBoard scoreBoard;
    private final AbstractGame currentGame;

    public ScoreController(AbstractGame currentGame, ScoreBoard scoreBoard) {
        this.currentGame = currentGame;
        this.scoreBoard = scoreBoard;
    }

    public void scorePoint(Player player) {
        scoreBoard.addPoint(player);

        if (currentGame.isGameOver()) {
            currentGame.onGameOver();
            currentGame.resetPoints();
        }
    }

    public void pointService() {
        if (currentGame.isPlayer0Service()) {
            scorePoint(currentGame.getPlayers().get(0));
        } else {
            scorePoint(currentGame.getPlayers().get(1));
        }
    }

    public void pointRest() {
        if (currentGame.isPlayer0Service()) {
            scorePoint(currentGame.getPlayers().get(1));
        } else {
            scorePoint(currentGame.getPlayers().get(0));
        }
    }

    public void lackService() {
        scoreBoard.incrementServiceFault();
        Logger.getLogger(ScoreController.class.getName()).info(ConstantsUtil.SERVICE_FAULT + scoreBoard.getServiceFaultCount());

        if (scoreBoard.getServiceFaultCount() >= 2) {
            Logger.getLogger(ScoreController.class.getName()).info(ConstantsUtil.POINT_AWARDED_TO_THE_RECEIVER);
            if (currentGame.isPlayer0Service()) {
                scorePoint(currentGame.getPlayers().get(1));
            } else {
                scorePoint(currentGame.getPlayers().get(0));
            }
            scoreBoard.resetServiceFaultCount();
        }
    }
}
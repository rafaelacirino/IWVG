package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.utils.ConstantsUtil;
import es.upm.game.tennis.view.CommandHandler;

import java.util.logging.Logger;

public class ScoreController {

    private final ScoreBoard scoreBoard;
    private final MatchScore matchScore;
    private AbstractGame currentGame;

    public ScoreController(MatchScore matchScore, AbstractGame currentGame, ScoreBoard scoreBoard) {
        this.matchScore = matchScore;
        this.currentGame = currentGame;
        this.scoreBoard = scoreBoard;
    }

    private void checkGameEnd() {
        if (currentGame.isGameOver()) {
            currentGame.onGameOver();

            Player winner = currentGame.getPlayers().get(
                    currentGame.getCurrentPoints()[0] > currentGame.getCurrentPoints()[1] ? 0 : 1
            );

            scoreBoard.addPoint(winner);
            checkSetEnd();
        }
    }

    private void checkSetEnd() {
        if (matchScore.getCurrentSet().isSetOver()) {
            Logger.getLogger(ScoreController.class.getName()).info(ConstantsUtil.SET_BALL);

            if (matchScore.isMatchOver()) {
                return;
            }

            matchScore.getCurrentSet().resetGames();
        }
    }

    private void resetPoints() {
        currentGame.resetPoints();
    }

    public void scorePoint(Player player) {
        currentGame.addPoint(player);
        checkGameEnd();
        currentGame.resetPoints();
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
            Logger.getLogger(CommandHandler.class.getName()).info(ConstantsUtil.POINT_AWARDED_TO_THE_RECEIVER);
            if (currentGame.isPlayer0Service()) {
                scorePoint(currentGame.getPlayers().get(1));
            } else {
                scorePoint(currentGame.getPlayers().get(0));
            }
            scoreBoard.resetServiceFaultCount();
        }
    }
}
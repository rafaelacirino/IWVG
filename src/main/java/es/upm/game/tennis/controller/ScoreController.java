package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;

import java.util.logging.Logger;

public class ScoreController {

    private final ScoreBoard scoreBoard;
    private final MatchScore matchScore;
    private AbstractGame currentGame;

    private static final Logger logger = Logger.getLogger(ScoreController.class.getName());

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
            logger.info("¡Set terminado!");

            if (matchScore.isMatchOver()) {
                logger.info("¡Partido terminado!");
                return;
            }

            matchScore.getCurrentSet().resetGames();
            logger.info("Iniciando nuevo set");
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
        logger.info("Service fault! Current fault count: " + scoreBoard.getServiceFaultCount());

        if (scoreBoard.getServiceFaultCount() >= 2) {
            logger.info("Two consecutive service faults! Point awarded to the receiver.");
            if (currentGame.isPlayer0Service()) {
                scorePoint(currentGame.getPlayers().get(1));
            } else {
                scorePoint(currentGame.getPlayers().get(0));
            }
            scoreBoard.resetServiceFaultCount();
        }
    }
}
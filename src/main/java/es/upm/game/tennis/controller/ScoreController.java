package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.*;
import es.upm.game.tennis.view.MatchView;

import java.util.logging.Logger;

public class ScoreController {

    private ScoreBoard scoreBoard;
    private IGame game;

    private static final Logger logger = Logger.getLogger(ScoreController.class.getName());

    public ScoreController(ScoreBoard scoreBoard, IGame game) {
        this.scoreBoard = scoreBoard;
        this.game = game;
    }

    private void checkGameEndAndSwitchRoles() {
        if (scoreBoard.isGameOver()) {
            logger.info("Game ball!");
            game.switchRoles();
            scoreBoard.resetPoints();

            if (scoreBoard.isSetOver()) {
                logger.info("Set ball!");
                scoreBoard.updateSets(game.isPlayer0Service() ? game.getPlayers().get(0) : game.getPlayers().get(1));
                scoreBoard.resetGames();
            }
        }
    }

//    private void scorePoint(boolean isPlayer0Service) {
//        if (scoreBoard.getServiceFaultCount() > 0) {
//            scoreBoard.resetServiceFaultCount();
//        }
//        if (isPlayer0Service) {
//            scoreBoard.getCurrentPoints()[0]++;
//        } else {
//            scoreBoard.getCurrentPoints()[1]++;
//        }
//    }

    private void scorePoint(Player player) {
        scoreBoard.updatePoints(player);
        checkGameEndAndSwitchRoles();
    }

    public void pointService() {
        if (game.isPlayer0Service()) {
            logger.info("Player 0 (Server) scored a point, %s");
            System.out.println("CURRENT POINTS: " + scoreBoard.getCurrentPoints()[0]);
            scorePoint(game.getPlayers().get(0));
            System.out.println("CURRENT POINTS: " + scoreBoard.getCurrentPoints()[0]);
        } else {
            logger.info("Player 1 (Server) scored a point");
            scorePoint(game.getPlayers().get(1));
        }
    }

    public void pointRest() {
        if (game.isPlayer0Service()) {
            logger.info("Player 1 (Receiver) scored a point");
            scorePoint(game.getPlayers().get(1));
        } else {
            logger.info("Player 0 (Receiver) scored a point");
            scorePoint(game.getPlayers().get(0));
        }
    }

//    public void pointService() {
//        if (game.isPlayer0Service()) {
//            scoreBoard.updatePoints(game.getPlayers().get(0));
//        } else {
//            scoreBoard.updatePoints(game.getPlayers().get(1));
//        }
//        checkGameEndAndSwitchRoles();
//    }

//    public void pointRest() {
//        if (game.isPlayer0Service()) {
//            scoreBoard.updatePoints(game.getPlayers().get(1));
//        } else {
//            scoreBoard.updatePoints(game.getPlayers().get(0));
//        }
//        checkGameEndAndSwitchRoles();
//    }

    public void lackService() {
        scoreBoard.incrementServiceFault();
        logger.info("Service fault! Current fault count: " + scoreBoard.getServiceFaultCount());

        if (scoreBoard.getServiceFaultCount() >= 2) {
            logger.info("Two consecutive service faults! Point awarded to the receiver.");
            if (game.isPlayer0Service()) {
                scorePoint(game.getPlayers().get(1));
//                scoreBoard.updatePoints(game.getPlayers().get(1));
            } else {
                scorePoint(game.getPlayers().get(0));
//                scoreBoard.updatePoints(game.getPlayers().get(0));
            }
            scoreBoard.resetServiceFaultCount();
        }
    }

//    private void checkGameEndAndSwitchRoles() {
//        if (scoreBoard.isGameOver()) {
//            game.switchRoles();
//            scoreBoard.resetPoints();
//            if (scoreBoard.isSetOver()) {
//                scoreBoard.updateSets(game.isPlayer0Service() ? game.getPlayers().get(0) : game.getPlayers().get(1));
//                scoreBoard.resetGames();
//            }
//        }
//    }
}
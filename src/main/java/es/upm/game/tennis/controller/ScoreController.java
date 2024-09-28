package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.Game;
import es.upm.game.tennis.model.Match;
import es.upm.game.tennis.model.ScoreBoard;

import java.util.logging.Logger;

public class ScoreController {

    private ScoreBoard scoreBoard;
    private Game game;

    private static final Logger logger = Logger.getLogger(ScoreController.class.getName());

    public ScoreController() {
    }

    public ScoreController(ScoreBoard scoreBoard, Game game) {
        this.scoreBoard = scoreBoard;
        this.game = game;
    }

    private void checkGameEndAndSwitchRoles() {
        if (isGameOver()) {
            logger.info("Game ball!");
            game.switchRoles();
            scoreBoard.resetPoints();

            if (isSetOver()) {
                logger.info("Set ball!");
                scoreBoard.updateSets(game.isPlayer0Service() ? game.getPlayers().get(0) : game.getPlayers().get(1));
                scoreBoard.resetGames();
            }
        }
    }

    private void scorePoint(boolean isPlayer0Service) {
        if (isPlayer0Service) {
            scoreBoard.getCurrentPoints()[0]++;
        } else {
            scoreBoard.getCurrentPoints()[1]++;
        }
    }

    public void pointService() {
        if (game.isPlayer0Service()) {
            scoreBoard.updatePoints(game.getPlayers().get(0));
        } else {
            scoreBoard.updatePoints(game.getPlayers().get(1));
        }
        checkGameEndAndSwitchRoles();
    }

    public void pointRest() {
        if (game.isPlayer0Service()) {
            scoreBoard.updatePoints(game.getPlayers().get(1));
        } else {
            scoreBoard.updatePoints(game.getPlayers().get(0));
        }
        checkGameEndAndSwitchRoles();
    }

    public void lackService() {
        scoreBoard.incrementServiceFault();
        logger.info("Service fault committed by: " + (game.isPlayer0Service() ? game.getPlayers().get(0).getName() : game.getPlayers().get(1).getName()));

        if (scoreBoard.getServiceFaultCount() == 2) {
            logger.info("Double fault! Point awarded to the receiver.");
            if (game.isPlayer0Service()) {
                scoreBoard.updatePoints(game.getPlayers().get(1));
            } else {
                scoreBoard.updatePoints(game.getPlayers().get(0));
            }

            scoreBoard.resetServiceFaultCount();
            checkGameEndAndSwitchRoles();
        }
    }

    public boolean isGameOver() {
        return scoreBoard.isGameOver();
    }

    public boolean isSetOver() {
        return scoreBoard.isSetOver();
    }

//    public void lackService() {
//        if (currentServer != null) {
//            logger.info("Lack of service by: " + currentServer.getName());
//            lackServiceCount++;
//
//            if(lackServiceCount == 2){
//                givePointToOponent();
//                lackServiceCount = 0;
//            }
//
//        } else {
//            logger.info("No player is currently serving.");
//        }
//    }

//
//    private void givePointToOponent(){
//        if(currentServer.equals(playerService)){
//            logger.info("Point awarded to: " + playerService.getName());
//        }
//    }
}
package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.Game;
import es.upm.game.tennis.model.Match;
import es.upm.game.tennis.model.Player;

import java.util.logging.Logger;

public class ScoreController {

    private Match match;
    private Player currentServer;
    private Player playerService;
    private Player player;
    private int lackServiceCount = 0;

    private static final Logger logger = Logger.getLogger(ScoreController.class.getName());

    public ScoreController(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public Player getPlayerService() {
        return match.getPlayerService();
    }

    public Player getPlayerRest() {
        return match.getPlayerRest();
    }

    public Player getCurrentServer() {
        return currentServer;
    }

    private void checkGameEndAndSwitchRoles() {
        Game currentGame = match.getCurrentGame();
        if (currentGame.isGameOver()) {
            match.switchRoles();
            match.startNewGame();
        }
    }

    public void pointService() {
        Game currentGame = match.getCurrentGame();
        currentGame.addPoint(match.getPlayerService());
        checkGameEndAndSwitchRoles();
    }

    public void pointRest() {
        Game currentGame = match.getCurrentGame();
        currentGame.addPoint(match.getPlayerRest());
        checkGameEndAndSwitchRoles();
    }

    public boolean isGameOver() {
        return match.getCurrentGame().isGameOver();
    }

    public void lackService() {

        if (currentServer != null) {
            logger.info("Lack of service by: " + currentServer.getName());
            lackServiceCount++;

            if(lackServiceCount == 2){
                givePointToOponent();
                lackServiceCount = 0;
            }

        } else {
            logger.info("No player is currently serving.");
        }
    }

    private void givePointToOponent(){
        if(currentServer.equals(playerService)){
            logger.info("Point awarded to: " + playerService.getName());
        }
    }
}
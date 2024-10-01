package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.AbstractGame;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.StandardGame;
import es.upm.game.tennis.model.TieBreakGame;

public class GameController {
    public AbstractGame createGame(boolean isTieBreak, Player playerService, Player playerRest) {
        if (isTieBreak) {
            return new TieBreakGame(playerService, playerRest);
        } else {
            return new StandardGame(playerService, playerRest);
        }
    }
}
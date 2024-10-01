package es.upm.game.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class StandardGame extends AbstractGame {

    public StandardGame(Player playerService, Player playerRest) {
        super(playerService, playerRest);
    }

    @Override
    public boolean isGameOver() {
        return gameScore.isGameOver();
    }
}
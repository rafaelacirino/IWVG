package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Referee;

import java.util.logging.Logger;

public class RefereeView {

    public void displayRefereeCreated(Referee referee) {
        Logger.getLogger(RefereeView.class.getName())
                .info("Referee created: " + referee.getName());
    }

    public void displayLoginStatus(boolean success) {
        Logger.getLogger(RefereeView.class.getName())
                .info(String.format("Login %s", success ? "successful" : "failed"));
    }
}
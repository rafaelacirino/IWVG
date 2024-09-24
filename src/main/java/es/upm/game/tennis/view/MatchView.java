package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;

public class MatchView {

    public void displayMatchScore(String matchScore) {
        System.out.println("Current Match Score: " + matchScore);
    }

    public void promptCommand() {
        System.out.print("> ");
    }

    public void displayPlayerCreated(Player player) {
        System.out.println("Player created: " + player.getName());
    }

    public void displayRefereeCreated(Referee referee) {
        System.out.println("Referee created: " + referee.getName());
    }

    public void displayLoginStatus(boolean success) {
        System.out.println("Login " + (success ? "successful!" : "failed!"));
    }
}
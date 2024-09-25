package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.Referee;

import java.util.ArrayList;
import java.util.List;

public class RefereeController {

    private List<Referee> referees;

    public RefereeController() {
        referees = new ArrayList<>();
    }

    public Referee createReferee(String name, String password) {
        Referee referee = new Referee(referees.size() + 1, name, password);
        referees.add(referee);
        return referee;
    }

    public boolean login(String name, String password) {
        for (Referee referee : referees) {
            if (referee.getName().equals(name) && referee.authenticate(password)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicate(String name, String password) {
        return referees.stream()
                .anyMatch(referee -> referee.getName().equals(name) && referee.authenticate(password));
    }
}
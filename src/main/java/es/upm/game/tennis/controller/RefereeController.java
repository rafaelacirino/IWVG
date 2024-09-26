package es.upm.game.tennis.controller;

import es.upm.game.tennis.model.Referee;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RefereeController {

    private final List<Referee> referees;
    private static final Logger logger = Logger.getLogger(String.valueOf(RefereeController.class));


    public RefereeController() {
        referees = new ArrayList<>();
    }

    public Referee createReferee(String name, String password) {
        Referee referee = new Referee(referees.size() + 1, name, password);
        if (isDuplicate(name, password)){
            logger.info("Referee name already exists. Create another one.");
        } else{
            referees.add(referee);
        }
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
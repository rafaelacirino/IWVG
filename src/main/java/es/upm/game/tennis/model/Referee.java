package es.upm.game.tennis.model;

public class Referee {

    private final String name;
    private final String password;

    public Referee(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
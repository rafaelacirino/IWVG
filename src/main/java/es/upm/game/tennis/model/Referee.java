package es.upm.game.tennis.model;

public class Referee {

    private int id;
    private String name;
    private String password;

    public Referee(int id, String name, String password) {
        this.id = id;
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
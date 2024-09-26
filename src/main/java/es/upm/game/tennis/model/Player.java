package es.upm.game.tennis.model;

public class Player {

    private final int id;
    private final String name;
    private int currentPoints;
    private boolean isServing;
    private final int[] gamesWonPerSet;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.currentPoints = 0;
        this.gamesWonPerSet = new int[5];
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void scorePoint() {
        currentPoints++;
    }
}
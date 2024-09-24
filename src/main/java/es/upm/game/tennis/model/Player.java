package es.upm.game.tennis.model;

public class Player {

    private int id;
    private String name;
    private int currentPoints;
    private boolean isServing;
    private int[] gamesWonPerSet;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.currentPoints = 0;
        this.gamesWonPerSet = new int[10]; // Assuming a maximum of 10 sets
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

    public int getGamesWonPerSet(int setIndex) {
        return gamesWonPerSet[setIndex];
    }

    public void winGamePerSet(int setIndex) {
        gamesWonPerSet[setIndex]++;
    }

    public void scorePoint() {
        currentPoints++;
    }

    public void resetPoints() {
        currentPoints = 0;
    }

    public void startServing() {
        isServing = true;
    }

    public void stopServing() {
        isServing = false;
    }

    public boolean isServing() {
        return isServing;
    }
}
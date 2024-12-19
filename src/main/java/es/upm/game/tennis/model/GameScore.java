package es.upm.game.tennis.model;

public class GameScore {
    private final int[] points = {0, 0};

    public void addPoint(int playerIndex) {
        points[playerIndex]++;
    }

    public void resetPoints() {
        points[0] = 0;
        points[1] = 0;
    }

    public int[] getPoints() {
        return points;
    }
}

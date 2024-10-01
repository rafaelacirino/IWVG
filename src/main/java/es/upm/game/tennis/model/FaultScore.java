package es.upm.game.tennis.model;

public class FaultScore {
    private int serviceFaultCount = 0;

    public void incrementFault() {
        serviceFaultCount++;
    }

    public int getFaultCount() {
        return serviceFaultCount;
    }

    public void resetFaultCount() {
        serviceFaultCount = 0;
    }
}

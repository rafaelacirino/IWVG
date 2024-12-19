package es.upm.game.tennis.view;

import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.controller.*;

import java.util.logging.Logger;

public class MatchView {

    public String getMatchScore(MatchController matchController) {
        String currentServer = matchController.getMatch().getCurrentSet()
                .getCurrentGame().isPlayer0Service() ? "*" : " ";
        String currentReceiver = !matchController.getMatch().getCurrentSet()
                .getCurrentGame().isPlayer0Service() ? "*" : " ";

        Player player1 = matchController.getMatch().getCurrentSet().getCurrentGame().getPlayers().get(0);
        Player player2 = matchController.getMatch().getCurrentSet().getCurrentGame().getPlayers().get(1);

        int[] points = matchController.getMatch().getScoreBoard().getCurrentPoints();

        return String.format("%n%s %s: %d%n",
                currentServer, player1.getName(), points[0]) +
               String.format("%s %s: %d%n",
                currentReceiver, player2.getName(), points[1]);
    }

    public void displayInitialMatch(MatchController matchController) {
        StringBuilder matchScore = new StringBuilder();
        matchScore.append(String.format("date: %s%n", matchController.getMatch().getDate()))
                  .append(getMatchScore(matchController));
        Logger.getLogger(MatchView.class.getName()).info(String.format("Init Match:%n%s", matchScore));
    }

    public void displayMatchScore(MatchController matchController) {
        String matchScore = getMatchScore(matchController);
        Logger.getLogger(MatchView.class.getName()).info(String.format("Current Match Score:%n%s", matchScore));
    }
}
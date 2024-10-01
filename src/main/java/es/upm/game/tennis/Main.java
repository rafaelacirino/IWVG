package es.upm.game.tennis;

import es.upm.game.tennis.controller.MatchController;
import es.upm.game.tennis.controller.PlayerController;
import es.upm.game.tennis.controller.RefereeController;
import es.upm.game.tennis.view.*;

public class Main {
    public static void main(String[] args) {
        MatchView matchView = new MatchView();
        RefereeView refereeView = new RefereeView();
        PlayerView playerView = new PlayerView();
        PlayerController playerController = new PlayerController();
        RefereeController refereeController = new RefereeController();
        MatchController matchController = new MatchController(matchView);

        CommandHandler commandHandler = new CommandHandler(matchController, playerController, refereeController, matchView, playerView, refereeView);
        GameManager gameManager = new GameManager(commandHandler);

        gameManager.start();
    }
}
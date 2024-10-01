package es.upm.game.tennis;

import es.upm.game.tennis.controller.CommandController;
import es.upm.game.tennis.controller.MatchController;
import es.upm.game.tennis.controller.PlayerController;
import es.upm.game.tennis.controller.RefereeController;
import es.upm.game.tennis.view.*;

public class Main {
    public static void main(String[] args) {
        MatchView matchView = new MatchView();
        RefereeView refereeView = new RefereeView();
        RefereeController refereeController = new RefereeController();
        PlayerController playerController = new PlayerController();
        MatchController matchController = new MatchController(matchView);
        CommandController commandController = new CommandController(matchView, refereeController, playerController);

        CommandHandler commandHandler = new CommandHandler(matchController, refereeController, matchView, refereeView, commandController);
        GameManager gameManager = new GameManager(commandHandler);

        gameManager.start();
    }
}
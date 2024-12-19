package es.upm.game.tennis;

import es.upm.game.tennis.controller.CommandController;
import es.upm.game.tennis.controller.MatchController;
import es.upm.game.tennis.controller.PlayerController;
import es.upm.game.tennis.controller.RefereeController;
import es.upm.game.tennis.view.CommandHandler;
import es.upm.game.tennis.view.GameManager;
import es.upm.game.tennis.view.MatchView;

public class Application {
    public void run() {
        MatchView matchView = new MatchView();
        RefereeController refereeController = new RefereeController();
        PlayerController playerController = new PlayerController();
        MatchController matchController = new MatchController();
        CommandController commandController = new CommandController(
                matchController, matchView, refereeController, playerController);

        CommandHandler commandHandler = new CommandHandler(commandController);
        GameManager gameManager = new GameManager(commandHandler);

        gameManager.start();
    }
}

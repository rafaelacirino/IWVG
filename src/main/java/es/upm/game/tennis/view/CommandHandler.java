package es.upm.game.tennis.view;

import es.upm.game.tennis.controller.CommandController;
import es.upm.game.tennis.utils.ConstantsUtil;

import java.util.logging.Logger;

public class CommandHandler {

    private final CommandController commandController;

    public CommandHandler(CommandController commandController) {
        this.commandController = commandController;
    }

    public void handleCommand(String command) {
        switch (command) {
                case "createReferee":
                    commandController.createReferee();
                    break;

                case "login":
                    commandController.login();
                    break;

                case "createPlayer":
                    commandController.createPlayer();
                    break;

                case "readPlayer":
                    commandController.readPlayer();
                    break;

                case "readPlayers":
                    commandController.readPlayers();
                    break;

                case "createMatch":
                    commandController.createMatch();
                    break;

                case "lackService", "pointService", "pointRest":
                    commandController.handleMatchAction(command);
                    break;

                default:
                    Logger.getLogger(CommandHandler.class.getName()).warning(ConstantsUtil.UNKNOWN_COMMAND);
                    break;
            }
    }
}
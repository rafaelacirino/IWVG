package es.upm.game.tennis.view;

import es.upm.game.tennis.controller.CommandController;
import es.upm.game.tennis.utils.ConstantsUtil;
import es.upm.game.tennis.controller.MatchController;
import es.upm.game.tennis.controller.RefereeController;
import es.upm.game.tennis.model.Referee;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class CommandHandler {

    private MatchController matchController;
    private final RefereeController refereeController;
    private final MatchView matchView;
    private final RefereeView refereeView;
    private final CommandController commandController;

    private static final Map<String, Consumer<MatchController>> pointActions = new HashMap<>();

    static {
        pointActions.put("lackService", MatchController::lackService);
        pointActions.put("pointService", MatchController::pointService);
        pointActions.put("pointRest", MatchController::pointRest);
    }

    public CommandHandler(MatchController matchController, RefereeController refereeController, MatchView matchView,
                          RefereeView refereeView, CommandController commandController) {
        this.matchController = matchController;
        this.refereeController = refereeController;
        this.matchView = matchView;
        this.refereeView = refereeView;
        this.commandController = commandController;
    }

    public void handleCommand(String command) {
        switch (command) {
                case "createReferee":
                    String refName = commandController.getInput(ConstantsUtil.REFEREE_NAME);
                    String refPassword = commandController.getInput(ConstantsUtil.REFEREE_PASSWORD);
                    Referee referee = refereeController.createReferee(refName, refPassword);
                    refereeView.displayRefereeCreated(referee);
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

                case "readMatch":
                    matchView.displayMatchResult(matchController);
                    break;

                default:
                    Logger.getLogger(CommandHandler.class.getName()).warning(ConstantsUtil.UNKNOWN_COMMAND);
                    break;
            }
    }
}
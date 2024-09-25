package es.upm.game.tennis;

import es.upm.game.tennis.controller.*;
import es.upm.game.tennis.model.Match;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;
import es.upm.game.tennis.service.MatchService;
import es.upm.game.tennis.view.MatchView;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        MatchView matchView = new MatchView();
        PlayerController playerController = new PlayerController();
        RefereeController refereeController = new RefereeController();
        Match match = new Match();
        MatchService matchService = new MatchService(match);
        MatchController matchController = null;

        boolean running = true;

        while (running) {
            matchView.promptCommand();
            String command = scanner.nextLine();

            switch (command) {
                case "createReferee":
                    String refName = getInput("Enter referee name: ");
                    String refPassword = getInput("Enter referee password: ");
                    Referee referee = refereeController.createReferee(refName, refPassword);
                    matchView.displayRefereeCreated(referee);
                    break;

                case "login":
                    String loginName = getInput("Enter referee name: ");
                    String loginPassword = getInput("Enter referee password: ");
                    boolean success = refereeController.login(loginName, loginPassword);
                    matchView.displayLoginStatus(success);
                    break;

                case "createPlayer":
                    String playerName = getInput("Enter player name: ");
                    Player player = playerController.createPlayer(playerName);
                    matchView.displayPlayerCreated(player);
                    break;

                case "createMatch":
                    int totalSets = getInputNumber("Enter number of sets: ");
                    int id1 = getInputNumber("Enter player id 1: ");
                    int id2 = getInputNumber("Enter player id 2: ");

                    Player player1 = playerController.getPlayerById(id1).orElse(null);
                    Player player2 = playerController.getPlayerById(id2).orElse(null);

                    if (player1 != null && player2 != null) {
                        Match newMatch = new Match(totalSets, player1, player2);
                        matchController = new MatchController(newMatch, matchView, matchService);
                        matchController.createMatch(totalSets, player1, player2);
                    } else {
                        System.out.println("One or both players not found.");
                    }
                    break;

                case "readPlayers":
                    matchController.readPlayers();
                    break;

                case "lackService":
                    if (matchController != null) {
                        matchController.lackService();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "pointService":
                    if (matchController != null) {
                        matchController.pointService();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "pointRest":
                    if (matchController != null) {
                        matchController.pointRest();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "addPointToServer":
                    if (matchController != null) {
                        matchController.pointToServer();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "addPointToReceiver":
                    if (matchController != null) {
                        matchController.pointToReceiver();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "displayScore":
                    if (matchController != null) {
                        matchController.displayScore();
                    } else {
                        System.out.println("No match is active.");
                    }
                    break;

                case "endMatch":
                    if (matchController != null && matchService.isGameOver()) {
                        System.out.println("The match has ended.");
                    } else {
                        System.out.println("Match is still ongoing or no match has been started.");
                    }
                    break;

                case "logout":
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }
    }

    private static String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int getInputNumber(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }
}
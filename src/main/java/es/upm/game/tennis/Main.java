package es.upm.game.tennis;

import es.upm.game.tennis.controller.*;
import es.upm.game.tennis.model.Player;
import es.upm.game.tennis.model.Referee;
import es.upm.game.tennis.view.MatchView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlayerController playerController = new PlayerController();
        RefereeController refereeController = new RefereeController();
        MatchController matchController = new MatchController();
        MatchView matchView = new MatchView();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            matchView.promptCommand();
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "createplayer":
                    System.out.println("Enter player name: ");
                    String playerName = scanner.nextLine();
                    Player player = playerController.createPlayer(playerName);
                    matchView.displayPlayerCreated(player);
                    break;

                case "createreferee":
                    System.out.println("Enter referee name: ");
                    String refName = scanner.nextLine();
                    System.out.println("Enter referee password: ");
                    String refPassword = scanner.nextLine();
                    Referee referee = refereeController.createReferee(refName, refPassword);
                    matchView.displayRefereeCreated(referee);
                    break;

                case "login":
                    System.out.println("Enter referee name: ");
                    String loginName = scanner.nextLine();
                    System.out.println("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    boolean success = refereeController.login(loginName, loginPassword);
                    matchView.displayLoginStatus(success);
                    break;

                case "readplayers":
                    List<Player> players = playerController.getPlayers();
                    for (Player p : players) {
                        System.out.println("Player ID: " + p.getId() + ", Name: " + p.getName());
                    }
                    break;

                case "readplayer":
                    System.out.println("Enter player ID: ");
                    int playerId = scanner.nextInt();
                    Player foundPlayer = playerController.getPlayerById(playerId);
                    if (foundPlayer != null) {
                        System.out.println("Player ID: " + foundPlayer.getId() + ", Name: " + foundPlayer.getName());
                    } else {
                        System.out.println("Player not found.");
                    }
                    break;

                case "creatematch":
                    System.out.print("Enter ID of Player 1: ");
                    int id1 = scanner.nextInt();
                    System.out.print("Enter ID of Player 2: ");
                    int id2 = scanner.nextInt();
                    System.out.println("Enter number of sets: ");
                    int totalSets = scanner.nextInt();
                    Player playerService = playerController.getPlayerById(id1);
                    Player playerRest = playerController.getPlayerById(id2);
                    matchController.createMatch(totalSets, playerService, playerRest);
                    break;

                case "lackservice":
                    matchController.addPointToReceiver();
                    matchView.displayMatchScore(matchController.getMatchScore());
                    break;

                case "pointservice":
                    matchController.addPointToServer();
                    matchView.displayMatchScore(matchController.getMatchScore());
                    break;

                case "pointrest":
                    matchController.addPointToReceiver();
                    matchView.displayMatchScore(matchController.getMatchScore());
                    break;

                case "readmatch":
                    System.out.println("Enter match ID: ");
                    int matchId = scanner.nextInt();
                    String matchScore = matchController.getMatchScore();
                    System.out.println("Match Score: " + matchScore);
                    break;

                case "logout":
                    running = false;
                    break;

                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }

        scanner.close();
    }
}
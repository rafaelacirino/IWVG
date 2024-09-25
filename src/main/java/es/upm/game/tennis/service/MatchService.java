package es.upm.game.tennis.service;

import es.upm.game.tennis.model.Game;
import es.upm.game.tennis.model.Match;
import es.upm.game.tennis.model.Player;

public class MatchService {

    private Match match;
    private Player currentServer;
    private Player currentReceiver;

    public MatchService(Match match) {
        this.match = match;
    }

    public void createMatch(int totalSets, Player player1, Player player2) {
        Player playerService = Math.random() < 0.5 ? player1 : player2;
        Player playerRest = (playerService == player1) ? player2 : player1;
        match = new Match(totalSets, playerService, playerRest);
    }

    public void addPointToServer() {
        Game currentGame = match.getCurrentGame();
        currentGame.addPoint(match.getPlayerService());
        checkGameEndAndSwitchRoles();
    }

    public void addPointToReceiver() {
        Game currentGame = match.getCurrentGame();
        currentGame.addPoint(match.getPlayerRest());
        checkGameEndAndSwitchRoles();
    }

    private void checkGameEndAndSwitchRoles() {
        Game currentGame = match.getCurrentGame();
        if (currentGame.isGameOver()) {
            match.switchRoles();
            match.startNewGame();
        }
    }

    public boolean isMatchOver() {
        return match.isMatchOver();
    }

    public String getMatchScore() {
        return match.getMatchScore();
    }

    public void pointService() {
        Game currentGame = match.getCurrentGame();
        currentGame.addPoint(match.getPlayerService());
        checkGameEndAndSwitchRoles();
    }

    public void pointRest() {
        Game currentGame = match.getCurrentGame();
        currentGame.addPoint(match.getPlayerRest());
        checkGameEndAndSwitchRoles();
    }

    public boolean isGameOver() {
        return match.getCurrentGame().isGameOver();
    }

    public Player getCurrentServer() {
        return match.getPlayerService();
    }

    public Player getCurrentReceiver() {
        return match.getPlayerRest();
    }

    public void lackService() {
        // Se o jogador atual que está servindo é conhecido
        if (currentServer != null) {
            // Exibir mensagem de falta de serviço
            System.out.println("Lack of service by: " + currentServer.getName());

            // Mudar o jogador que está servindo e o que está recebendo
            switchPlayers(); // Método que troca os papéis de server e receiver

        } else {
            System.out.println("No player is currently serving.");
        }
    }

    private void switchPlayers() {
        Player temp = currentServer;
        currentServer = currentReceiver;
        currentReceiver = temp;
    }
}
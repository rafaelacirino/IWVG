package es.upm.game.tennis.view;

import java.util.Scanner;

public class GameManager {

    private final CommandHandler commandHandler;
    private final Scanner scanner = new Scanner(System.in);

    public GameManager(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine();
            if (command.equals("logout")) {
                running = false;
                System.out.println("Exiting...");
            } else {
                commandHandler.handleCommand(command);
            }
        }
    }
}
package es.upm.game.tennis.view;

import es.upm.game.tennis.utils.ConstantsUtil;

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
            if (command.equals(ConstantsUtil.LOGOUT)) {
                running = false;
                System.out.println(ConstantsUtil.EXITING);
            } else {
                commandHandler.handleCommand(command);
            }
        }
    }
}
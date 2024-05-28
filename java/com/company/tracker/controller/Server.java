package com.company.tracker.controller;

import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.CommandProvider;
import com.company.tracker.controller.command.Input;
import com.company.tracker.controller.command.impl.Undefined;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Server {

    private static boolean running = true;

    private static Command lastCommand;
    private static Scanner input;

    public static void run() {
        initialize();

        System.out.println("Learning Progress Tracker");
        while (running) {
            String inputString = input.nextLine();
            String possibleCommandName = inputString.split(" ")[0];

            String response = null;
            if (inputString.isBlank()) {
                response = "No input.";
                continue;
            } else {
                response = executeTask(inputString);
            }
            System.out.println(response);

        }

    }

    private static String executeTask(String input) {
        CommandProvider commandProvider = new CommandProvider();
        Command command = commandProvider.getCommand(input);

        if (command instanceof Undefined) {
            if (lastCommand instanceof Input) {
                return lastCommand.execute(input);
            }
            return command.execute(input);
        }
        lastCommand = command;
        return command.execute(input);
    }


    private static void initialize() {
        input = new Scanner(System.in);
    }
    public static void close(){
        running = false;
    }
}

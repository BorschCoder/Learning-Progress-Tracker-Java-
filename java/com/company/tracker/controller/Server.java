package com.company.tracker.controller;

import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.CommandProvider;
import com.company.tracker.controller.command.Input;
import com.company.tracker.controller.command.impl.BackCommand;
import com.company.tracker.controller.command.impl.ExitCommand;
import com.company.tracker.controller.command.impl.UndefinedCommand;

import java.util.Scanner;

public class Server {

    private static boolean running = true;

    private static boolean isInput = false;
    private static Command lastCommand;
    private static Scanner input;

    public static void run() {
        initialize();

        System.out.println("Learning Progress Tracker");

        while (running) {

            String inputString = input.nextLine();
            String response = null;

            if (inputString.isBlank() & lastCommand == null) {
                response = "No input.";
            } else {
                response = executeTask(inputString);
            }

            System.out.println(response);

        }

    }

    private static String executeTask(String input) {
        CommandProvider commandProvider = new CommandProvider();
        Command command = commandProvider.getCommand(input);

        if (command instanceof UndefinedCommand || command instanceof ExitCommand) {
            if (lastCommand instanceof Input && isInput) {
                return lastCommand.execute(input);
            }
            return command.execute(input);
        }
        if (!(command instanceof ExitCommand) && !isInput) {
            if (command instanceof BackCommand & !(lastCommand instanceof BackCommand)) {
                return "Enter 'exit' to exit the program.";
            }
        }

        lastCommand = command;
        return command.execute(input);
    }

    public static void enableInputMode() {
        isInput = true;
    }

    public static void disableInputMode() {
        isInput = false;
    }

    public static boolean inputMode() {
        return isInput;
    }

    private static void initialize() {
        input = new Scanner(System.in);
    }

    public static void close() {
        running = false;
    }
}

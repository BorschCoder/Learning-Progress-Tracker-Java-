package com.company.tracker;

import com.company.tracker.controller.Controller;
import com.company.tracker.controller.ResponseType;
import com.company.tracker.controller.Command;

import java.util.ResourceBundle;
import java.util.Scanner;

import static com.company.tracker.controller.ResponseType.*;
import static com.company.tracker.controller.Command.*;

public class Main {
    private static boolean running = true;

    private static ResourceBundle bundle;
    public static final String RESPONSE_BUNDLE = "response";

    public static void main(String[] args) {

        bundle = ResourceBundle.getBundle(RESPONSE_BUNDLE);

        System.out.println(bundle.getString(ADDED.name()));
        System.out.println(obtainString(HELP));

         System.out.println("Learning Progress Tracker");
        while (running) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.isBlank()){
                System.out.println("No input.");
                return;
            }
            else if (command.equals(EXIT.getCommand())){
                running = false;
                System.out.println("Bye!");
            }
            ResponseType respons = Controller.hundleCommand(Command.valueOf(command));

        }
    }

    private static String obtainString(Enum type) {
        return bundle.getString(type.name());
    }
}

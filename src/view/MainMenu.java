package view;

import controller.Controller;

import java.util.Scanner;

public class MainMenu extends Menu{
    private Controller controller;

    public MainMenu(Controller controller) {
        this.controller = controller;
    }

    public String run() {
        String command;

        while (true) {
            command = getScanner().nextLine();
            if (command.matches("^\\s*teacher\\s+menu\\s*$")) {
                if (controller.isLoggedInUserStudent())
                    System.out.println("vorood namo'tabar");
                else {
                    System.out.println("varede tecaherMenu shodid");
                    return "teacher menu";
                }
            } else if (command.matches("^\\s*student\\s+menu\\s*$")) {
                if (!controller.isLoggedInUserStudent())
                    System.out.println("vorood namo'tabar");
                else {
                    System.out.println("varede studentMenu shodid");
                    return "student menu";
                }
            } else if (command.matches("^\\s*logout\\s*$")) {
                System.out.println(controller.logout());
                System.out.println("kharej shodid");
                return "logout";
            } else
                System.out.println("namo'tabar!");
        }
    }
}
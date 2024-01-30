package view;

import controller.Controller;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu extends Menu{
    private Controller controller;

    public LoginMenu(Controller controller) {
        this.controller = controller;
    }

    public String run() {
        Matcher matcher;
        String command, result;

        while (true) {
            Scanner sc=new Scanner(System.in);
            command =sc.nextLine();

            if (command.matches("^\\s*exit\\s*$"))
                return "exit";

            if ((matcher =getMatcher(command, "^\\s*register\\s+(?<role>\\S+)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$")) != null)
                System.out.println(controller.register(matcher.group("username"), matcher.group("password"), matcher.group("role")));
            else if ((matcher =getMatcher(command, "^\\s*login\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$")) != null) {
                result = controller.login(matcher.group("username"), matcher.group("password"));
                System.out.println(result);
                if (result.equals("login movaffag bood"))
                    return "Login anjam shod";
            } else {
                System.out.println("namo'tabar!");
            }
        }
    }
}
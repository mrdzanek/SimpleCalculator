package pl.simplecalc;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static boolean exitFlag = false;
    private static Pattern digits = Pattern.compile("[0-9]*");
    private static Pattern command = Pattern.compile("[+\\-*/=]");
    private static String input = "";
    private static double memory;
    private static double num;

    public static void main(String[] args) {

        while (!exitFlag) {
            try {
                mainLoop();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                sc.next();
            }
        }
    }

    private static void mainLoop() throws InputMismatchException {

        while (true) {
            System.out.println("Current value: " + memory);
            System.out.println("Current argument: " + num);
            input = sc.next();
            Matcher m1 = digits.matcher(input);

            if (input.equals("end")) {
                exitFlag = true;
                return;
            } else if (m1.matches()) {
                num = Double.valueOf(input);
            } else {
                memory = executeCommand(input);
            }
        }
    }

    private static double executeCommand(String input) {
        Matcher m2 = command.matcher(input);

        if (m2.matches()) {
            switch (input) {
                case "+": {
                    return num + memory;
                }
                case "-": {
                    return memory - num;
                }
                case "*": {
                    return memory * num;
                }
                case "/": {
                    if (num != 0) {
                        return memory / num;
                    } else {
                        System.out.println("Cannot divide by 0!");
                        return memory;
                    }
                }
                case "=": {
                    System.out.println("Result: " + memory);
                    return memory;
                }
                default:
                    System.out.println("Illegal value!");
            }
        } else {
            System.out.println("Not a command");
        }
        return 0;
    }
}

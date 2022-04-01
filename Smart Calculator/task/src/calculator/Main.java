package calculator;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        while (true) {
            processInput();
        }
    }

    private static void processInput() {
        String input = scanner.nextLine().trim();
        if (input.startsWith("/")) {
            runCommand(input);
            return;
        }
        String[] line = input.split(" ");
        int out = 0;
        int tmpNum = 0;
        char operation = '+';
        for (int i = 0; i < line.length; i++) {
            if (i % 2 == 0) {
                try {
                    tmpNum = Integer.parseInt(line[i]);
                } catch (Exception e) {
                    if (!(line.length == 1 && line[0].equals(""))) {
                        System.out.println("Invalid expression");
                    }
                    return;
                }
                out = calc(out, tmpNum, operation);
            } else {
                operation = defineOperation(line[i]);
            }
        }
        System.out.println(out);
    }

    private static int calc(int out, int num, char operation) {
        return switch (operation) {
            case '+' -> out + num;
            case '-' -> out - num;
            default -> throw new IllegalArgumentException();
        };
    }

    private static void runCommand(String command) {
        switch (command) {
            case "/exit" -> {
                System.out.println("Bye!");
                System.exit(0);
            }
            case "/help" -> System.out.println("The program calculates the sum of numbers");
            default -> System.out.println("Unknown command");
        }
    }

    private static char defineOperation(String opGroup) {
        int minus = 0;
        char[] operations = opGroup.toCharArray();
        for (var ch : operations) {
            if (ch == '-') {
                minus++;
            }
        }
        if (minus > 0 && minus % 2 != 0) {
            return '-';
        }
        return '+';
    }
}
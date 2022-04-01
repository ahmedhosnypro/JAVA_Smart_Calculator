package calculator;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final HashMap<String, Integer> varMap = new HashMap<>();
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
        String input = scanner.nextLine().trim().replaceAll("[+]", " + ")
                .replaceAll("[-]", " - ")
                .replaceAll("[=]", " = ")
                .replaceAll("\\s+", " ");
        if (input.startsWith("/")) {
            runCommand(input);
        } else if ((input.contains("="))) {
            assignVariable(input);
        } else if (!input.equals("")) {
            calcWithCheck(input);
        }
    }

    private static void calcWithCheck(String input) {
        if (checkIdentifiers(input) && isKnownIdentifiers(input)) {
            Integer result = calc(input);
            if (result != null) {
                System.out.println(result);
            }
        } else if (!checkIdentifier(input)) {
            System.out.println("Invalid identifier");
        } else if (!isKnownIdentifiers(input)) {
            System.out.println("Unknown variable");
        }
    }

    private static void assignVariable(String input) {
        String right = input.substring(0, input.indexOf("=")).trim();
        String left = input.substring(input.indexOf("=") + 1).trim();
        if (checkIdentifier(right) && !left.contains("=") && checkIdentifiers(left) && isKnownIdentifiers(left)) {
            Integer leftResult = calc(left);
            if (leftResult != null) {
                varMap.put(right, leftResult);
            }
        } else if (!checkIdentifier(right)) {
            System.out.println("Invalid identifier");
        } else if (left.contains("=") || !checkIdentifiers(left)) {
            System.out.println("Invalid assignment");
        } else if (!isKnownIdentifiers(left)) {
            System.out.println("Unknown variable");
        }
    }

    private static boolean checkIdentifiers(String identifiers) {
        for (var identifier : identifiers.split(" ")) {
            if (identifier.matches(".*[a-zA-Z].*") && !checkIdentifier(identifier)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkIdentifier(String identifier) {
        return identifier.matches("[a-zA-Z]+");
    }

    private static boolean isKnownIdentifiers(String input) {
        for (var identifier : input.split(" ")) {
            if (identifier.matches("[a-zA-Z]+") && !varMap.containsKey(identifier)) {
                return false;
            }
        }
        return true;
    }

    private static Integer calc(String operations) {
        int result = 0;
        String operation = "+";
        boolean isOperationsSequence = false;
        for (var val : operations.split(" ")) {
            if (val.matches("\\w.*") && varMap.containsKey(val)) {
                if (varMap.get(val) != null) {
                    result = calc(result, varMap.get(val), operation);
                    isOperationsSequence = false;
                }
            } else if (val.matches("\\d+")) {
                try {
                    int num = Integer.parseInt(val);
                    result = calc(result, num, operation);
                    isOperationsSequence = false;
                } catch (NumberFormatException ignored) {
                    System.out.println("error");
                    return null;
                }
            } else if (val.matches("[+-/*]")) {
                if (isOperationsSequence) {
                    if (operation.equals("-") && val.equals("-")) {
                        operation = "+";
                    } else {
                        operation = val;
                    }
                } else {
                    operation = val;
                    isOperationsSequence = true;
                }
            }
        }
        return result;
    }

    private static int calc(int out, int num, String operation) {
        switch (operation) {
            case "+":
                return out + num;
            case "-":
                return out - num;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void runCommand(String command) {
        switch (command) {
            case "/exit":
                System.out.println("Bye!");
                System.exit(0);
                break;
            case "/help":
                System.out.println("The program calculates the sum of numbers");
                break;
            default:
                System.out.println("Unknown command");
        }
    }
}
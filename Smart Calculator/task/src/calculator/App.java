package calculator;

import java.math.BigInteger;
import java.util.HashMap;

import static calculator.Assigner.assignVariable;
import static calculator.Evaluator.evaluate;
import static calculator.Reader.read;

public class App {
    private App() {
    }

    private static final HashMap<String, BigInteger> varMap = new HashMap<>();

    static void start() {
        while (true) {
            processInput();
        }
    }

    static void processInput() {
        String input = read();
        if (input.matches("\\s*/\\s*\\w+")) {
            runCommand(input);
        } else if ((input.contains("="))) {
            assignVariable(varMap, input);
        } else if (!input.equals("")) {
            evaluate(varMap, input);
        }
    }

    private static void runCommand(String command) {
        command = command.replaceAll(" ", "");
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

package calculator;

import java.math.BigInteger;
import java.util.Map;

import static calculator.ExpressionChecker.checkExpression;
import static calculator.IdentifierChecker.checkIdentifiers;
import static calculator.IdentifierChecker.isKnownIdentifiers;

public class Evaluator {
    private Evaluator() {
    }

    static void evaluate(Map<String, BigInteger> varMap, String input) {
        if (checkExpression(input) && checkIdentifiers(input) && isKnownIdentifiers(varMap, input)) {
            BigInteger result = PostfixCalculator.calc(varMap, input);
            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println("evaluation error");
            }
        } else if (!checkIdentifiers(input)) {
            System.out.println("Invalid identifier");
        } else if (!checkExpression(input)) {
            System.out.println("Invalid expression");
        } else if (!isKnownIdentifiers(varMap, input)) {
            System.out.println("Unknown variable");
        }
    }
}

package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            String[] line = input.split(" ");
            if (line.length == 1 && !line[0].equals("")) {
                if (input.equalsIgnoreCase("/exit")) {
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                } else if (input.equalsIgnoreCase("/help")) {
                    System.out.println("The program calculates the sum of numbers");
                } else {
                    System.out.println(line[0]);
                }
            } else if (line.length > 1) {
                int out = 0;
                int tmpNum = 0;
                char operation = '+';
                for (int i = 0; i < line.length; i++) {
                    if (i % 2 == 0) {
                        tmpNum = Integer.parseInt(line[i]);
                    } else {
                        operation = defineOperation(line[i]);
                        out = calc(out, tmpNum, operation);
                    }
                }
                out = calc(out, tmpNum, operation);
                System.out.println(out);
            }
        }
    }

    private static int calc(int out, int num, char operation) {
        return switch (operation) {
            case '+' -> out + num;
            case '-' -> out - num;
            default -> throw new IllegalArgumentException();
        };
    }

    private static char defineOperation(String opGroup) {
        int minus = 0;
        char[] operations = opGroup.toCharArray();
        for (var ch : operations) {
            if (ch == '-') {
                minus++;
            }
        }
        if (minus % 2 != 0 || minus == 0) {
            return '+';
        }
        return '-';
    }
}

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
                int sum = 0;
                for (var n : line) {
                    sum += Integer.parseInt(n);
                }
                System.out.println(sum);
            }
        }
    }
}

package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("/exit")) {
                System.out.println("Bye!");
                System.exit(0);
                break;
            }
            String[] line = input.split(" ");
            if (line.length == 1 && !line[0].equals("")) {
                System.out.println(line[0]);
            } else if (line.length == 2) {
                int n1 = Integer.parseInt(line[0]);
                int n2 = Integer.parseInt(line[1]);
                System.out.println(n1 + n2);
            }
        }


    }
}

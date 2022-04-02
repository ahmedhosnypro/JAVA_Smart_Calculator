package calculator;

import java.util.Scanner;

public class Reader {
    private Reader() {
    }

    private static final Scanner scanner = new Scanner(System.in);

    static String read() {
        return scanner.nextLine().trim()
                .replaceAll("[+]", " + ")
                .replaceAll("[-]", " - ")
                .replaceAll("[*]", " * ")
                .replaceAll("[/]", " / ")
                .replaceAll("[\\^]", " ^ ")
                .replaceAll("[(]", " ( ")
                .replaceAll("[)]", " ) ")
                .replaceAll("[=]", " = ")
                .replaceAll("\\s+", " ");
    }
}

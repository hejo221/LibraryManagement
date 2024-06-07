package library.util;

import java.util.Scanner;
import java.util.InputMismatchException;

public class InputUtil {
    private final Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    private <T> T getInput(String prompt, String error, Class<T> type, boolean allowNumerals) {
        while (true) {
            try {
                System.out.println(prompt);
                if (type == Integer.class) {
                    int input = scanner.nextInt();
                    if (input < 0) {
                        throw new InputMismatchException(error);
                    }
                    scanner.nextLine();
                    return type.cast(input);
                } else if (type == Double.class) {
                    double input = scanner.nextDouble();
                    if (input < 0) {
                        throw new InputMismatchException(error);
                    }
                    scanner.nextLine();
                    return type.cast(input);
                } else if (type == String.class) {
                    String input = scanner.nextLine();
                    if (input.trim().isEmpty()) {
                        System.out.println(error);
                    } else if (!allowNumerals && input.matches(".*\\d.*")) {
                        System.out.println("Numeral values are not allowed. " + error);
                    } else {
                        return type.cast(input);
                    }
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }
    }

    public int getIntInput(String prompt, String error) {
        return getInput(prompt, error, Integer.class, true);
    }

    public double getDoubleInput(String prompt, String error) {
        return getInput(prompt, error, Double.class, true);
    }

    public String getStringInput(String prompt, String error) {
        return getInput(prompt, error, String.class, false);
    }

    public String getStringInput(String prompt, String error, boolean allowNumerals) {
        return getInput(prompt, error, String.class, allowNumerals);
    }
}
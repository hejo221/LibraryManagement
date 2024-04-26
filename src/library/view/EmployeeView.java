package library.view;

import library.controller.LibraryController;
import library.model.Employee;
import library.model.Employee.EmployeeStatus;

import java.util.Scanner;

public class EmployeeView {
    private LibraryController libraryController;
    private Scanner scanner;

    public EmployeeView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
    }

    public void displayLogin() {
        System.out.println("Employee Login: ");
        System.out.println("Please enter your Employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        boolean loggedIn = libraryController.getEmployeeController().login(employeeID, password);
        if (loggedIn) {
            System.out.println("Login successful!");
            displayOptions();
        } else {
            System.out.println("Invalid credentials! Please try again.");
            displayLogin();
        }
    }

    public void displayOptions() {
        System.out.println("\nYour options are:");
        System.out.println("1: ");
        System.out.println("2: ");
        System.out.println("3: ");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

        displayOptions();
    }
}

package library.view;

import library.Main;
import library.controller.LibraryController;
import library.model.Employee;

import java.util.Scanner;

public class MainView {
    private LibraryController libraryController;
    private Scanner scanner;

    public MainView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
    }

    public void displayLogin() {
        System.out.println("~Please to login to access the Library Management~\n");
        System.out.println("Enter your Employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter your password: ");
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
        Employee loggedInEmployee = libraryController.getEmployeeController().getLoggedInEmployee();

        System.out.println("~~~ Welcome to the Library! ~~~\n");
        System.out.println("Here are your options:");
        System.out.println("1: Manage the Media Catalogue");
        System.out.println("2: Manage Media Copies");
        System.out.println("3: Manage Customer Accounts");
        System.out.println("4: Manage Employees");
        System.out.println("5: Logout");
        System.out.println("6: Quit the application");

        System.out.println("\nPlease enter your choice (1-5): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                MediaView mediaView = new LibraryView(libraryController).getMediaView();
                mediaView.displayGeneralOptions();
                break;
            case 2:
                // TO-DO
                break;
            case 3:
                CustomerView customerView = new LibraryView(libraryController).getCustomerView();
                customerView.displayOptions();
                break;
            case 4:
                if (loggedInEmployee != null && loggedInEmployee.getEmployeeStatus() == Employee.EmployeeStatus.MANAGER) {
                    EmployeeView employeeView = new LibraryView(libraryController).getEmployeeView();
                    employeeView.displayOptions();
                } else {
                    System.out.println("Access denied. Only managers can manage employees.");
                }
                break;
            case 5:
                // TO-DO
                break;
            case 6:
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again!");
        }

        displayOptions();
    }
}

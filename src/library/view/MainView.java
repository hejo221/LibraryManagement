package library.view;

import library.controller.LibraryController;
import library.exceptions.FileWriteException;
import library.model.Employee;
import library.util.InputUtil;
import library.util.WriteUtil;
import src.library.view.CustomerView;

import java.util.Scanner;

public class MainView {
    private LibraryController libraryController;
    private final Scanner scanner;
    private InputUtil inputUtil;

    public MainView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
        this.inputUtil = new InputUtil(scanner);
    }

    public void displayLogin() {
        System.out.println("Please to login to access the Library Management\n");

        int employeeID = inputUtil.getIntInput("Enter your employee ID: ", "Invalid input. Enter a numeric employee ID.");
        String password = inputUtil.getStringInput("Enter your password: ", "The password cannot be empty.", true);

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

        System.out.println("\n~~~ Welcome to the Library! ~~~\n");
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
                CopyView copyView = new LibraryView(libraryController).getCopyView();
                copyView.displayOptions();
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
                libraryController.getEmployeeController().logout();
                System.out.println("Logout successful.");
                displayLogin();
                break;
            case 6:
                try {
                    WriteUtil.writeEmployeesToFile(libraryController.getEmployeeController().getEmployeeDB());
                    WriteUtil.writeCustomersToFile(libraryController.getCustomerController().getCustomerDB());
                    WriteUtil.writeCopiesToFile(libraryController.getCopyController().getCopyDB());
                    WriteUtil.writeMediaToFile(libraryController.getMediaController().getMediaDB());
                } catch (FileWriteException exception) {
                    System.out.println("Error writing to file: " + exception.getMessage());
                }
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again!");
        }

        displayOptions();
    }
}

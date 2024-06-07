package library.view;

import library.controller.LibraryController;
import library.model.Employee;
import library.util.InputUtil;

import java.util.Scanner;

public class EmployeeView {
    private LibraryController libraryController;
    private Scanner scanner;
    private InputUtil inputUtil;

    public EmployeeView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
        this.inputUtil = new InputUtil(scanner);
    }

    public void displayOptions() {
        System.out.println("\nYour options are:");
        System.out.println("1: Print all Employees");
        System.out.println("2: Find specific Employee");
        System.out.println("3: Add new Employee");
        System.out.println("4: Update Employee Name");
        System.out.println("5: Update Employee Salary");
        System.out.println("6: Update Employee Role");
        System.out.println("7: Remove Employee");
        System.out.println("8: Go back to the main menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                displayAllEmployees();
                break;
            case 2:
                findEmployeeByID();
                break;
            case 3:
                addNewEmployee();
                break;
            case 4:
                updateEmployeeName();
                break;
            case 5:
                updateEmployeeSalary();
                break;
            case 6:
                updateEmployeeStatus();
                break;
            case 7:
                removeEmployee();
                break;
            case 8:
                MainView mainView = new LibraryView(libraryController).getMainView();
                mainView.displayOptions();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        displayOptions();
    }

    private void displayAllEmployees() {
        libraryController.getEmployeeController().printAllEmployees();
        displayOptions();
    }

    private void findEmployeeByID() {
        System.out.println("Searching for a specific employee: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the employee ID to search for: ", "Invalid input. Enter a numeric employee ID.");

        Employee employee = libraryController.getEmployeeController().findEmployee(id);

        if (employee != null) {
            System.out.println("Employee found: " + employee);
        } else {
            System.out.println("Employee not found.");
        }

        displayOptions();
    }

    private void addNewEmployee() {
        System.out.println("Adding a new employee:");
        scanner.nextLine();

        String firstName = inputUtil.getStringInput("Enter the first name of the employee.", "The first name of the customer cannot be empty.");
        String familyName = inputUtil.getStringInput("Enter the family name of the employee.", "The family name of the customer cannot be empty.");
        double salary = inputUtil.getDoubleInput("Enter the salary of the employee: ", "The salary must not be negative.");
        String status = inputUtil.getStringInput("Enter the status of the employee (INTERN, EMPLOYEE, MANAGER): ", "The status of the employee cannot be empty.").toUpperCase();
        String password = inputUtil.getStringInput("Enter the password of the employee: ", "The password of the employee cannot be empty.");

        Employee.EmployeeStatus employeeStatus = Employee.EmployeeStatus.valueOf(status);
        Employee employee = libraryController.getEmployeeController().addNewEmployee(firstName, familyName, salary, employeeStatus, password);

        if (employee != null) {
            System.out.println("The new employee has been created successfully.");
            System.out.println("New employee: " + employee);
        } else {
            System.out.println("Failed to add the new employee.");
        }

        displayOptions();
    }

    private void updateEmployeeName() {
        System.out.println("Updating the name of an existing employee: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the ID of the employee to update: ", "Invalid input. Enter a numeric employee ID.");

        Employee employee = libraryController.getEmployeeController().findEmployee(id);

        if (employee != null) {
            String newFirstName = inputUtil.getStringInput("Enter the new first name: ", "The new first name cannot be empty.");
            String newFamilyName = inputUtil.getStringInput("Enter the new family name: ", "The new family name cannot be empty.");

            boolean updateSuccess = libraryController.getEmployeeController().updateEmployeeName(id, newFirstName, newFamilyName);
            if (updateSuccess) {
                System.out.println("The employee has been updated successfully.");
            } else {
                System.out.println("Failed to update the employee.");
            }
        } else {
            System.out.println("Employee not found.");
        }

        displayOptions();
    }

    private void updateEmployeeSalary() {
        System.out.println("Updating the salary of an existing employee: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the ID of the employee to update: ", "Invalid input. Enter a numeric employee ID.");

        Employee employee = libraryController.getEmployeeController().findEmployee(id);

        if (employee != null) {
            double newSalary = inputUtil.getDoubleInput("Enter the new first salary: ", "The new salary must not be negative.");

            boolean updateSuccess = libraryController.getEmployeeController().updateEmployeeSalary(id, newSalary);
            if (updateSuccess) {
                System.out.println("The employee has been updated successfully.");
            } else {
                System.out.println("Failed to update the employee.");
            }
        } else {
            System.out.println("Employee not found.");
        }

        displayOptions();
    }

    private void updateEmployeeStatus() {
        System.out.println("Updating the status of an existing employee: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the ID of the employee to update: ", "Invalid input. Enter a numeric employee ID.");

        Employee employee = libraryController.getEmployeeController().findEmployee(id);

        if (employee != null) {
            String newStatus = inputUtil.getStringInput("Enter the new status (INTERN, EMPLOYEE, MANAGER): : ", "The new status cannot be empty.").toUpperCase();
            Employee.EmployeeStatus employeeStatus = Employee.EmployeeStatus.valueOf(newStatus);

            boolean updateSuccess = libraryController.getEmployeeController().updateEmployeeStatus(id, employeeStatus);
            if (updateSuccess) {
                System.out.println("The employee has been updated successfully.");
            } else {
                System.out.println("Failed to update the employee.");
            }
        } else {
            System.out.println("Employee not found.");
        }

        displayOptions();
    }

    private void removeEmployee() {
        System.out.println("Removing a employee: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the ID of the employee to remove: ", "Invalid input. Enter a numeric employee ID.");

        Employee loggedInEmployee = libraryController.getEmployeeController().getLoggedInEmployee();

        if (loggedInEmployee != null && loggedInEmployee.getEmployeeID() == id) {
            System.out.println("You cannot remove yourself.");
            displayOptions();
        }

        Employee employee = libraryController.getEmployeeController().findEmployee(id);

        if (employee != null) {
            System.out.println("The employee has been found: " + employee);
            String confirmation = inputUtil.getStringInput("Are you sure that you want to remove this customer? (yes/no): ", "Invalid input. Please enter 'yes' or 'no'!");
            if (confirmation.equalsIgnoreCase("yes")) {
                boolean removeSuccess = libraryController.getEmployeeController().removeEmployee(id);
                if (removeSuccess) {
                    System.out.println("The employee has been removed successfully.");
                } else {
                    System.out.println("Failed to remove the employee.");
                }
            }
        } else {
            System.out.println("Employee not found.");
        }

        displayOptions();
    }
}
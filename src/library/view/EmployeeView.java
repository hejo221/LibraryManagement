package library.view;

import library.controller.LibraryController;
import library.model.Customer;
import library.model.Employee;
import library.model.Employee.EmployeeStatus;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeView {
    private LibraryController libraryController;
    private Scanner scanner;

    public EmployeeView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
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
                System.out.println("3");
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
            default:
                System.out.println("Invalid choice. Please try again.");
        }

        displayOptions();
    }

    public void displayAllEmployees() {
        libraryController.getEmployeeController().printAllEmployees();
        displayOptions();
    }

    public void findEmployeeByID() {
        System.out.println("Searching for a specific employee: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the Employee ID: ");
            int id = scanner.nextInt();

            Employee employee = libraryController.getEmployeeController().findEmployee(id);
            if (employee != null) {
                System.out.println("Employee found: " + employee);
            } else {
                System.out.println("Employee not found.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter a numeric employee ID.");
            scanner.nextLine();
        }

        displayOptions();
    }

    public void updateEmployeeName() {
        System.out.println("Updating the name of an employee: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the employee ID to update the name: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Employee employee = libraryController.getEmployeeController().findEmployee(id);
            if (employee != null) {
                System.out.println("The following employee will be updated: " + employee);
                String newFirstName, newFamilyName;

                while (true) {
                    System.out.println("Enter the new first name: ");
                    newFirstName = scanner.nextLine();
                    if (newFirstName.matches(".*\\d.*")) {
                        System.out.println("Numeral values are not allowed in names. Please try again: ");
                    } else {
                        break;
                    }
                }

                while (true) {
                    System.out.println("Enter the new family name: ");
                    newFamilyName = scanner.nextLine();
                    if (newFamilyName.matches(".*\\d.*")) {
                        System.out.println("Numeral values are not allowed in names. Please try again: ");
                    } else {
                        break;
                    }
                }

                boolean updateSuccess = libraryController.getEmployeeController().updateEmployeeName(id, newFirstName, newFamilyName);
                if (updateSuccess) {
                    System.out.println("The employee name has been updated successfully.");
                } else {
                    System.out.println("Failed to update employee name.");
                }
            } else {
                System.out.println("Employee not found.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter a valid ID.");
            scanner.nextLine();
        }

        displayOptions();
    }

    public void updateEmployeeSalary() {
        System.out.println("Updating the salary of an employee: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the employee ID to update the salary: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Employee employee = libraryController.getEmployeeController().findEmployee(id);
            if (employee != null) {
                System.out.println("The salary of the following employee will be updated: " + employee);

                System.out.println("Enter the new salary: ");
                while (true) {
                    try {
                        double newSalary = scanner.nextDouble();

                        boolean updateSuccess = libraryController.getEmployeeController().updateEmployeeSalary(id, newSalary);
                        if (updateSuccess) {
                            System.out.println("The salary of the employee has been updated successfully.");
                        } else {
                            System.out.println("Failed to update employee salary.");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number: ");
                        scanner.nextLine();
                    }
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number: ");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Error: " + exception.getMessage());
            scanner.nextLine();
        }

        displayOptions();
    }

    public void updateEmployeeStatus() {
        System.out.println("Updating the status of an employee: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the employee ID to update the status: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Employee employee = libraryController.getEmployeeController().findEmployee(id);
            if (employee != null) {
                System.out.println("The status of the following employee will be updated: " + employee);

                System.out.println("Enter the new status (INTERN, NORMAL, MANAGER): ");
                while (true) {
                    try {
                        String statusInput = scanner.nextLine().toUpperCase();
                        Employee.EmployeeStatus newStatus = Employee.EmployeeStatus.valueOf(statusInput);

                        boolean updateSuccess = libraryController.getEmployeeController().updateEmployeeStatus(id, newStatus);
                        if (updateSuccess) {
                            System.out.println("The status of the employee has been updated successfully.");
                        } else {
                            System.out.println("Failed to update employee status.");
                        }
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status. Please enter a valid status (INTERN, NORMAL, MANAGER): ");
                    }
                }
            } else {
                System.out.println("Employee not found.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Error: Invalid input. Please enter a valid ID: ");
            scanner.nextLine();
        }

        displayOptions();
    }

    public void removeEmployee() {
        System.out.println("Removing an employee: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the employee ID to remove: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Employee loggedInEmployee = libraryController.getEmployeeController().getLoggedInEmployee();
            if (loggedInEmployee != null && loggedInEmployee.getEmployeeID() == id) {
                System.out.println("You cannot remove yourself!");
                return;
            }

            Employee employee = libraryController.getEmployeeController().findEmployee(id);
            if (employee != null) {
                System.out.println("Employee found: " + employee);
                System.out.println("Are you sure that you want to remove this employee? (yes/no): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {
                    boolean removeSuccess = libraryController.getEmployeeController().removeEmployee(id);
                    if (removeSuccess) {
                        System.out.println("Employee removed successfully.");
                    } else {
                        System.out.println("Failed to remove employee.");
                    }
                } else {
                    System.out.println("Employee removal canceled.");
                }
            } else {
                System.out.println("Employee not found.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter a numeric employee ID.");
            scanner.nextLine();
        }

        displayOptions();
    }
}

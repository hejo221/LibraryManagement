package library.view;

import library.controller.LibraryController;
import library.model.Customer;
import library.model.Customer.MembershipStatus;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerView {
    private LibraryController libraryController;
    private Scanner scanner;

    public CustomerView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Your options are: ");
        System.out.println("1: Print all Customer Accounts");
        System.out.println("2: Find Specific Customer");
        System.out.println("3: Add new Customer");
        System.out.println("4: Update Customer");
        System.out.println("5: Remove Customer");
        System.out.println("6: Go back to the main menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                displayAllCustomers();
                break;
            case 2:
                findCustomerByID();
                break;
            case 3:
                addNewCustomer();
                break;
            case 4:
                updateCustomer();
                break;
            case 5:
                removeCustomer();
                break;
            case 6:
                MainView mainView = new MainView(libraryController);
                mainView.displayOptions();
                break;
            default:
                System.out.println("Invalid choice. Please try again!");
        }

        displayOptions();
    }

    public void displayAllCustomers() {
        libraryController.getCustomerController().printAllCustomers();
        displayOptions();
    }

    public void findCustomerByID() {
        System.out.println("Searching for a specific customer: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the Customer ID: ");
            int id = scanner.nextInt();

            Customer customer = libraryController.getCustomerController().findCustomer(id);
            if (customer != null) {
                System.out.println("Customer found: " + customer);
            } else {
                System.out.println("Customer not found.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter a numeric customer ID.");
            scanner.nextLine();
        }
        scanner.close();
        displayOptions();
    }

    public void addNewCustomer() {
        System.out.println("Adding a new customer:");
        scanner.nextLine();

        try {
            System.out.println("Enter first name of the customer: ");
            String firstName = scanner.nextLine();
            if (firstName.matches(".*\\d.*")) {
                throw new InputMismatchException("Numeral values are not allowed in names.");
            }

            System.out.println("Enter family name of the customer: ");
            String familyName = scanner.nextLine();
            if (familyName.matches(".*\\d.*")) {
                throw new InputMismatchException("Numeral values are not allowed in names.");
            }

            Customer customer = libraryController.getCustomerController().addNewCustomer(firstName, familyName);

            if (customer != null) {
                System.out.println("Customer has been created successfully.");
                System.out.println(customer);
            } else {
                System.out.println("Failed to add the new customer.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
        scanner.close();
        displayOptions();
    }

    public void updateCustomer() {
        System.out.println("Updating an existing customer: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the customer ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Customer customer = libraryController.getCustomerController().findCustomer(id);
            if (customer != null) {
                System.out.println("Enter the new first name: ");
                String newFirstName = scanner.nextLine();
                if (newFirstName.matches(".*\\d.*")) {
                    throw new InputMismatchException("Numeral values are not allowed in names.");
                }

                System.out.println("Enter the new family name: ");
                String newFamilyName = scanner.nextLine();
                if (newFamilyName.matches(".*\\d.*")) {
                    throw new InputMismatchException("Numeral values are not allowed in names.");
                }

                boolean updateSuccess = libraryController.getCustomerController().updateCustomer(id, newFirstName, newFirstName);
                if (updateSuccess) {
                    System.out.println("The customer has been updated successfully.");
                } else {
                    System.out.println("Failed to update customer.");
                }
            } else {
                System.out.println("Customer not found.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Error" + exception.getMessage());
            scanner.nextLine();
        }
        scanner.close();
        displayOptions();
    }

    public void removeCustomer() {
        System.out.println("Removing a customer: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the customer ID to remove: ");
            int id = scanner.nextInt();

            Customer customer = libraryController.getCustomerController().findCustomer(id);
            if (customer != null) {
                System.out.println("Customer found" + customer);
                System.out.println("Are you sure that you want to remove this customer? (yes/no): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {
                    boolean removeSuccess = libraryController.getCustomerController().removeCustomer(id);
                    if (removeSuccess) {
                        System.out.println("Customer removed successfully.");
                    } else {
                        System.out.println("Failed to remove customer.");
                    }
                } else {
                    System.out.println("Customer removal canceled.");
                }
            } else {
                System.out.println("Customer not found.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter a numeric customer ID.");
            scanner.nextLine();
        }
        scanner.close();
        displayOptions();
    }
}
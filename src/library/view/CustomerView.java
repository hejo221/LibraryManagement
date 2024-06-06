package library.view;

import library.controller.LibraryController;
import library.model.Customer;

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
        System.out.println("1: Print all customer accounts");
        System.out.println("2: Find specific customer");
        System.out.println("3: Add new customer");
        System.out.println("4: Update customer");
        System.out.println("5: Reactivate suspended customer account");
        System.out.println("6: Remove customer");
        System.out.println("7: Go back to the main menu");

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
                reactivateAccount();
                break;
            case 6:
                removeCustomer();
                break;
            case 7:
                MainView mainView = new LibraryView(libraryController).getMainView();
                mainView.displayOptions();
                break;
            default:
                System.out.println("Invalid choice. Please try again!");
        }
        displayOptions();
    }

    private String getStringInput(String prompt, String error) {
        String input;
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println(error);
            } else if (input.matches(".*\\d.*")) {
                System.out.println("Numeral values are not allowed. " + error);
            } else {
                break;
            }
        }
        return input;
    }

    private int getIntInput(String prompt, String error) {
        int input;
        while (true) {
            try {
                System.out.println(prompt);
                input = scanner.nextInt();
                if (input < 0) {
                    throw new InputMismatchException(error);
                }
                scanner.nextLine();
                break;
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }
        return input;
    }

    private void displayAllCustomers() {
        libraryController.getCustomerController().printAllCustomers();
        displayOptions();
    }

    private void findCustomerByID() {
        System.out.println("Searching for a specific customer by ID: ");
        scanner.nextLine();

        int id = getIntInput("Enter the customer ID to search for: ", "Invalid input. Enter a numeric customer ID.");

        Customer customer = libraryController.getCustomerController().findCustomer(id);
        if (customer != null) {
            System.out.println("Customer found: " + customer);
        } else {
            System.out.println("Customer not found.");
        }

        displayOptions();
    }

    private void addNewCustomer() {
        System.out.println("Adding a new customer:");
        scanner.nextLine();

        String firstName = getStringInput("Enter the first name of the customer: ", "The first name of the customer cannot be empty.");
        String familyName = getStringInput("Enter the family name of the customer: ", "The family name of the customer cannot be empty.");

        Customer customer = libraryController.getCustomerController().addNewCustomer(firstName, familyName);

        if (customer != null) {
            System.out.println("The new customer has been created successfully.");
            System.out.println("New customer: " + customer);
        } else {
            System.out.println("Failed to create the new customer.");
        }

        displayOptions();
    }

    private void updateCustomer() {
        System.out.println("Updating an existing customer: ");
        scanner.nextLine();

        int id = getIntInput("Enter the ID of the customer to update: ", "Invalid input. Enter a numeric customer ID.");

        Customer customer = libraryController.getCustomerController().findCustomer(id);

        if (customer != null) {
            String newFirstName = getStringInput("Enter the new first name: ", "The new first name cannot be empty.");
            String newFamilyName = getStringInput("Enter the new family name: ", "The new family name cannot be empty.");

            boolean updateSucess = libraryController.getCustomerController().updateCustomer(id, newFirstName, newFamilyName);
            if (updateSucess) {
                System.out.println("The customer has been updated successfully.");
            } else {
                System.out.println("Failed to update the customer.");
            }
        } else {
            System.out.println("Customer not found.");
        }

        displayOptions();
    }

    private void reactivateAccount() {
        System.out.println("Reactivating a suspended account: ");
        scanner.nextLine();

        int id = getIntInput("Enter the ID of the suspended customer: ", "Invalid input. Enter a numeric customer ID");

        Customer customer = libraryController.getCustomerController().findCustomer(id);

        if (customer != null && customer.getMembershipStatus() == Customer.MembershipStatus.SUSPENDED) {
            String confirmation = getStringInput("Please confirm that the outstanding has been paid to reactivate the customer account (yes/no):", "Invalid input. Please enter 'yes' or 'no'!");
            if (confirmation.equalsIgnoreCase("yes")) {
                boolean reactiveSuccess = libraryController.getCustomerController().updateCustomerStatus(id, Customer.MembershipStatus.ACTIVE);
                if (reactiveSuccess) {
                    System.out.println("The customer account has been sucessfully reactivated again.");
                } else {
                    System.out.println("Failed to reactivate the customer account.");
                }
            }
        } else {
            System.out.println(customer == null ? "Customer not found." : "The customer account is currently not suspended.");
        }
    }

    private void removeCustomer() {
        System.out.println("Removing a customer: ");
        scanner.nextLine();

        int id = getIntInput("Enter the ID of the customer to remove: ", "Invalid input. Enter a numeric customer ID.");

        Customer customer = libraryController.getCustomerController().findCustomer(id);

        if (customer != null) {
            System.out.println("The customer has been found: " + customer);
            String confirmation = getStringInput("Are you sure that you want to remove this customer? (yes/no): ", "Invalid input. Please enter 'yes' or 'no'!");
            if (confirmation.equalsIgnoreCase("yes")) {
                boolean removeSuccess = libraryController.getCustomerController().removeCustomer(id);
                if (removeSuccess) {
                    System.out.println("The customer has been removed successfully.");
                } else {
                    System.out.println("Failed to remove the customer.");
                }
            }
        } else {
            System.out.println("Customer not found.");
        }

        displayOptions();
    }
}
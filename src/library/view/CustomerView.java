package library.view;

import library.controller.LibraryController;
import library.model.Customer;
import library.util.InputUtil;
import library.util.SortUtil;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class CustomerView {
    private LibraryController libraryController;
    private Scanner scanner;
    private InputUtil inputUtil;

    public CustomerView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
        this.inputUtil = new InputUtil(scanner);
    }

    public void displayOptions() {
        System.out.println("Your options are: ");
        System.out.println("1: Print all customer accounts (unsorted)");
        System.out.println("2: Print all customer accounts (sorted alphabetically by family name)");
        System.out.println("3: Print all customer accounts (sorted numerically by ID)");
        System.out.println("4: Find specific customer");
        System.out.println("5: Add new customer");
        System.out.println("6: Update customer");
        System.out.println("7: Reactivate suspended customer account");
        System.out.println("8: Remove customer");
        System.out.println("9: Go back to the main menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                displayAllCustomers();
                break;
            case 2:
                displayAllCustomerSortedAlphabetically();
                break;
            case 3:
                displayAllCustomersSortedNumerically();
                break;
            case 4:
                findCustomerByID();
                break;
            case 5:
                addNewCustomer();
                break;
            case 6:
                updateCustomer();
                break;
            case 7:
                reactivateAccount();
                break;
            case 8:
                removeCustomer();
                break;
            case 9:
                MainView mainView = new LibraryView(libraryController).getMainView();
                mainView.displayOptions();
                break;
            default:
                System.out.println("Invalid choice. Please try again!");
        }
        displayOptions();
    }

    private void displayAllCustomers() {
        libraryController.getCustomerController().printAllCustomers(libraryController.getCustomerController().getCustomerDB());
        displayOptions();
    }

    private void displayAllCustomerSortedAlphabetically() {
        LinkedHashMap<Integer, Customer> sortedCustomers = SortUtil.sortByComparator(libraryController.getCustomerController().getCustomerDB(), new SortUtil.CustomerNameComparator());
        libraryController.getCustomerController().printAllCustomers(sortedCustomers);
    }

    private void displayAllCustomersSortedNumerically() {
        LinkedHashMap<Integer, Customer> sortedCustomers = SortUtil.sortByComparator(libraryController.getCustomerController().getCustomerDB(), new SortUtil.CustomerIDComparator());
        libraryController.getCustomerController().printAllCustomers(sortedCustomers);
    }

    private void findCustomerByID() {
        System.out.println("Searching for a specific customer by ID: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the customer ID to search for: ", "Invalid input. Enter a numeric customer ID.");

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

        String firstName = inputUtil.getStringInput("Enter the first name of the customer: ", "The first name of the customer cannot be empty.");
        String familyName = inputUtil.getStringInput("Enter the family name of the customer: ", "The family name of the customer cannot be empty.");

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

        int id = inputUtil.getIntInput("Enter the ID of the customer to update: ", "Invalid input. Enter a numeric customer ID.");

        Customer customer = libraryController.getCustomerController().findCustomer(id);

        if (customer != null) {
            String newFirstName = inputUtil.getStringInput("Enter the new first name: ", "The new first name cannot be empty.");
            String newFamilyName = inputUtil.getStringInput("Enter the new family name: ", "The new family name cannot be empty.");

            boolean updateSuccess = libraryController.getCustomerController().updateCustomer(id, newFirstName, newFamilyName);
            if (updateSuccess) {
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

        int id = inputUtil.getIntInput("Enter the ID of the suspended customer: ", "Invalid input. Enter a numeric customer ID");

        Customer customer = libraryController.getCustomerController().findCustomer(id);

        if (customer != null && customer.getMembershipStatus() == Customer.MembershipStatus.SUSPENDED) {
            String confirmation = inputUtil.getStringInput("Please confirm that the outstanding has been paid to reactivate the customer account (yes/no):", "Invalid input. Please enter 'yes' or 'no'!");
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

        int id = inputUtil.getIntInput("Enter the ID of the customer to remove: ", "Invalid input. Enter a numeric customer ID.");

        Customer customer = libraryController.getCustomerController().findCustomer(id);

        if (customer != null) {
            System.out.println("The customer has been found: " + customer);
            String confirmation = inputUtil.getStringInput("Are you sure that you want to remove this customer? (yes/no): ", "Invalid input. Please enter 'yes' or 'no'!");
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

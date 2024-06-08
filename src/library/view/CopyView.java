package library.view;

import library.controller.LibraryController;
import library.model.Copy;
import library.model.Customer;
import library.model.Employee;
import library.model.Media;
import library.util.InputUtil;
import library.util.SortUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class CopyView {
    private LibraryController libraryController;
    private Scanner scanner;
    private InputUtil inputUtil;

    public CopyView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
        this.inputUtil = new InputUtil(scanner);
    }

    public void displayOptions() {
        scanner.nextLine();

        System.out.println("\nYour options are: ");
        System.out.println("1: Print all copies (unsorted)");
        System.out.println("2: Print all copies (sorted by ID)");
        System.out.println("3: Search for specific copy");
        System.out.println("4: Add new copy");
        System.out.println("5: Remove copy");
        System.out.println("6: Register copy as borrowed");
        System.out.println("7: Register copy as returned");
        System.out.println("8: Extend copy return date");
        System.out.println("9: Return to main menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                printAllCopies();
                break;
            case 2:
                printAllCopiesSortedNumerically();
                break;
            case 3:
                findCopyByID();
                break;
            case 4:
                addNewCopy();
                break;
            case 5:
                removeCopy();
                break;
            case 6:
                registerCopyAsBorrowed();
                break;
            case 7:
                registerCopyAsReturned();
                break;
            case 8:
                extendedReturnDate();
                break;
            case 9:
                MainView mainView = new LibraryView(libraryController).getMainView();
                mainView.displayOptions();
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void printAllCopies() {
        libraryController.getCopyController().printAllCopies(libraryController.getCopyController().getCopyDB());
        displayOptions();
    }

    private void printAllCopiesSortedNumerically() {
        LinkedHashMap<Integer, Copy> sortedCopies = SortUtil.sortByComparator(libraryController.getCopyController().getCopyDB(), new SortUtil.CopyIDComparator());
        libraryController.getCopyController().printAllCopies(sortedCopies);
    }

    private void findCopyByID() {
        System.out.println("Searching for specific copy by ID: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the copy ID to search for: ", "Invalid input. Please enter a numeric copy ID.");

        Copy copy = libraryController.getCopyController().findCopy(id);
        if (copy != null) {
            System.out.println("Copy found: " + copy);
        } else {
            System.out.println("Copy not found.");
        }

        displayOptions();
    }

    private void addNewCopy() {
        System.out.println("Adding new copy: \n");
        scanner.nextLine();

        int mediaID = inputUtil.getIntInput("Enter the Media ID of the copy: ", "Media ID must not be negative.");

        Media media = libraryController.getMediaController().findMedia(mediaID);
        Copy copy = libraryController.getCopyController().addNewCopy(media);

        if (copy != null) {
            System.out.println("The copy has successfully been added.");
            System.out.println("New copy: " + copy);
        } else {
            System.out.println("Failed to add the new copy.");
        }

        displayOptions();
    }

    private void removeCopy() {
        System.out.println("Removing copy: ");

        int id = inputUtil.getIntInput("Enter the copy ID to remove: ", "Invalid input. Please enter a numeric copy ID.");

        Copy copy = libraryController.getCopyController().findCopy(id);
        if (copy != null) {
            System.out.println("Copy found: " + copy);
            String confirmation = inputUtil.getStringInput("Are you sure that you want to remove this copy? (yes/no): ", "Invalid input. Please enter 'yes' or 'no'.");

            if (confirmation.equalsIgnoreCase("yes")) {
                boolean removeSuccess = libraryController.getCopyController().removeCopy(id);
                if (removeSuccess) {
                    System.out.println("Copy removed successfully.");
                } else {
                    System.out.println("Failed to remove copy.");
                }
            } else {
                System.out.println("Copy removal canceled.");
            }
        } else {
            System.out.println("Copy not found.");
        }

        displayOptions();
    }

    public void registerCopyAsBorrowed() {
        System.out.println("Registering copy as borrowed: ");
        scanner.nextLine();

        int copyID = inputUtil.getIntInput("Enter the ID of the copy to be borrowed: ", "Invalid input. Please enter a numeric copy ID.");
        int customerID = inputUtil.getIntInput("Enter the ID of the customer: ", "Invalid input. Please enter a numeric customer ID.");

        Copy copy = libraryController.getCopyController().findCopy(copyID);
        Customer customer = libraryController.getCustomerController().findCustomer(customerID);
        Employee activeEmployee = libraryController.getEmployeeController().getLoggedInEmployee();

        if (copy != null && customer != null) {
            if (customer.getMembershipStatus() == Customer.MembershipStatus.SUSPENDED) {
                System.out.println("Membership of the customer is suspended. The copy cannot be borrowed.");
            } else {
                if (copy.isBorrowed()) {
                    System.out.println("The book is already borrowed.");
                } else {
                    boolean borrowSuccess = libraryController.getCopyController().borrowCopy(copy.getCopyID(), customer, activeEmployee);
                    if (borrowSuccess) {
                        System.out.println("The copy has successfully been borrowed.");
                        System.out.println("Borrowed copy: " + copy);
                    } else {
                        System.out.println("Failed to borrow copy.");
                    }
                }
            }
        } else {
            System.out.println(copy == null ? "Copy not found." : "Customer not found.");
        }

        displayOptions();
    }

    private void registerCopyAsReturned() {
        System.out.println("Registering copy as returned: ");
        scanner.nextLine();

        int copyID = inputUtil.getIntInput("Enter the ID of the copy to be returned: ", "Invalid input. Please enter a numeric copy ID.");

        Copy copy = libraryController.getCopyController().findCopy(copyID);

        if (copy != null) {
            if (LocalDate.now().isAfter(copy.getReturnDate())) {
                long daysLate = ChronoUnit.DAYS.between(copy.getReturnDate(), LocalDate.now());
                System.out.println("The copy has been returned " + daysLate + " days late.");
                System.out.println("The customer account will be suspended until the fine for the late return has been paid.");

                boolean suspendSuccess = libraryController.getCustomerController().updateCustomerStatus(copyID, Customer.MembershipStatus.SUSPENDED);
                if (suspendSuccess) {
                    System.out.println("The customer account has been suspended successfully.");
                } else {
                    System.out.println("Failed to suspend the customer account. Please suspend the account manually.");
                }
            }

            boolean returnSuccess = libraryController.getCopyController().returnCopy(copyID);
            if (returnSuccess) {
                System.out.println("The copy has successfully been returned.");
            } else {
                System.out.println("Failed to register copy as returned.");
            }
        } else {
            System.out.println("Copy not found.");
        }

        displayOptions();
    }

    private void extendedReturnDate() {
        System.out.println("Extending the return of a copy by 28 days: ");
        scanner.nextLine();

        int copyID = inputUtil.getIntInput("Enter the ID of the copy to be returned later: ", "Invalid input. Please enter a numeric copy ID.");

        Copy copy = libraryController.getCopyController().findCopy(copyID);

        if (copy != null) {
            boolean extendSuccess = libraryController.getCopyController().extendCopyReturnDate(copyID);
            if (extendSuccess) {
                System.out.println("The return date has successfully been extended.");
                System.out.println("The new return date is: " + copy.getReturnDate());
            } else {
                System.out.println("Failed to extend return date.");
            }
        } else {
            System.out.println("Copy not found.");
        }

        displayOptions();
    }
}

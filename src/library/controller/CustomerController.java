package library.controller;

import library.exceptions.FileReadException;
import library.model.Customer;
import library.model.Customer.MembershipStatus;
import library.util.ReadUtil;

import java.util.HashMap;

public class CustomerController {
    private HashMap<Integer, Customer> customerDB;

    public CustomerController() {
        this.customerDB = new HashMap<>();
        readCustomers();
    }

    private void readCustomers() {
        try {
            customerDB = ReadUtil.readCustomersFromFile();
        } catch (FileReadException exception) {
            System.out.println("Error reading customers from file: " + exception.getMessage());
        }
    }

    public void printAllCustomers(HashMap<Integer, Customer> customerDB) {
        if (customerDB.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customerDB.values()) {
                System.out.println(customer);
            }
        }
    }

    public Customer addNewCustomer(String firstName, String familyName) {
        Customer newCustomer = new Customer(firstName, familyName);
        customerDB.put(newCustomer.getCustomerID(), newCustomer);
        return newCustomer;
    }

    public boolean updateCustomer(int id, String newFirstName, String newFamilyName) {
        if (customerDB.containsKey(id)) {
            Customer customer = customerDB.get(id);
            customer.setFirstName(newFirstName);
            customer.setFamilyName(newFamilyName);
            return true;
        }
        return false;
    }

    public boolean updateCustomerStatus(int id, MembershipStatus newMembershipStatus) {
        if (customerDB.containsKey(id)) {
            Customer customer = customerDB.get(id);
            customer.setMembershipStatus(newMembershipStatus);
            return true;
        }
        return false;
    }

    public Customer findCustomer(int id) {
        return customerDB.get(id);
    }

    public boolean removeCustomer(int id) {
        if (customerDB.containsKey(id)) {
            customerDB.remove(id);
            return true;
        }
        return false;
    }

    public HashMap<Integer, Customer> getCustomerDB() {
        return customerDB;
    }
}

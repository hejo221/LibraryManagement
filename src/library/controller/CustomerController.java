package library.controller;

import library.model.Customer;
import library.model.Customer.MembershipStatus;

import java.util.HashMap;

public class CustomerController {
    private HashMap<Integer, Customer> customerDB;

    public CustomerController() {
        this.customerDB = new HashMap<>();
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

    public boolean updateCustomerState(int id, MembershipStatus newMembershipStatus) {
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
}

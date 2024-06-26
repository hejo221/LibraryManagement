package library.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Customer {
    private static final int MIN_CUSTOMER_ID = 100000;
    private static final int MAX_CUSTOMER_ID = 999999;
    private static final Set<Integer> allocatedCustomerIDs = new HashSet<>();
    private static final Random random = new Random();

    private int customerID;
    private String firstName;
    private String familyName;
    private MembershipStatus membershipStatus;

    public Customer(String firstName, String familyName) {
        this.customerID = generateCustomerID();
        this.firstName = firstName;
        this.familyName = familyName;
        this.membershipStatus = MembershipStatus.ACTIVE;
    }

    public Customer (int customerID, String firstName, String familyName, MembershipStatus membershipStatus) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.familyName = familyName;
        this.membershipStatus = membershipStatus;
    }

    private int generateCustomerID() {
        int randomID;

        do {
            randomID = random.nextInt(MAX_CUSTOMER_ID - MIN_CUSTOMER_ID + 1) + MIN_CUSTOMER_ID;
        } while (allocatedCustomerIDs.contains(randomID));

        allocatedCustomerIDs.add(randomID);
        return randomID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public static Customer readFromFile(String customerString) {
        String[] parts = customerString.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid data format.");
        }

        int customerID = Integer.parseInt(parts[0]);
        String firstName = parts[1];
        String familyName = parts[2];
        String status = parts[3];

        MembershipStatus membershipStatus = MembershipStatus.valueOf(status);

        return new Customer(customerID, firstName, familyName, membershipStatus);
    }

    public String writeToFile() {
        return getCustomerID() + "," + getFirstName() + "," + getFamilyName() + "," + getMembershipStatus();
    }

    @Override
    public String toString() {
        return "Customer - Customer ID: " + getCustomerID() + ", First Name: " + getFirstName() + ", Family Name: " + getFamilyName()
                + ", Membership Status: " + getMembershipStatus() + "\n";
    }

    public enum MembershipStatus {
        ACTIVE, SUSPENDED
    }
}

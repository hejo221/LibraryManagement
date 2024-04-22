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
        this.customerID = generateID();
        this.firstName = firstName;
        this.familyName = familyName;
        this.membershipStatus = MembershipStatus.ACTIVE;
    }

    private int generateID() {
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

    public enum MembershipStatus {
        ACTIVE, EXPIRED, SUSPENDED, CLOSED
    }
}
